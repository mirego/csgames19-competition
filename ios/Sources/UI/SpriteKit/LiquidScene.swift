import Foundation
import SpriteKit

final class LiquidScene: SKScene {

    private var liquidNode: SBDynamicWaterNode!
    private var leftSideNode: SKSpriteNode!
    private var rightSideNode: SKSpriteNode!

    private var liquidColors = [UIColor]()

    private var colorsUnits: Int {
        return liquidColors.count > maxColorsCount ? maxColorsCount : liquidColors.count
    }

    private var ingredientNodes = [IngredientNode]()

    private let maxColorsCount = 6
    private var colorLayerHeight: Float {
        return liquidSurfaceHeight / Float(maxColorsCount)
    }

    private let liquidSurfaceHeight: Float = 400
    private var liquidHeight: Float = 0
    private let topCenterNode = SKNode()
    private let gravity = SKFieldNode.linearGravityField(withVector: float3(0,1,0))
    private let colorsView = UIView()

    private let splashWidth: Float = 20
    private let splashForceMultiplier: CGFloat = 0.125

    private let kFixedTimeStep: CFTimeInterval = 1 / 500
    private var lastFrameTime: CFTimeInterval = 0
    private var hasReferenceFrameTime: Bool = false

    override func didMove(to view: SKView) {
        super.didMove(to: view)
        
        backgroundColor = .clear

        addChild(topCenterNode)

        updateGravity(units: 0)
        gravity.isEnabled = true
        gravity.categoryBitMask = 0x1 << 0
        gravity.strength = 10
        topCenterNode.addChild(gravity)

        let bottomFloorNode = SKNode()
        bottomFloorNode.position = CGPoint(x: size.width / 2, y: 0)
        bottomFloorNode.physicsBody = SKPhysicsBody(rectangleOf: CGSize(width: size.width, height: 10))
        bottomFloorNode.physicsBody?.isDynamic = false
        addChild(bottomFloorNode)

        liquidNode = SBDynamicWaterNode(width: Float(size.width), numJoints: 100, surfaceHeight: 0, fillColour: UIColor.clear)
        liquidNode.position = CGPoint(x: size.width / 2, y: 0)
        liquidNode.zPosition = 1.f
        addChild(liquidNode)

        liquidNode.setDefaultValues()

        leftSideNode = SKSpriteNode(color: UIColor.blue, size: CGSize(width: 10, height: size.height / 4 * 3 - 20))
        leftSideNode.position = CGPoint(x: size.width / 3 + 18, y: size.height / 4)
        leftSideNode.zRotation = CGFloat.pi / 16
        leftSideNode.physicsBody = SKPhysicsBody(rectangleOf: CGSize(width: 10, height: leftSideNode.size.height))
        leftSideNode.physicsBody?.isDynamic = false
        addChild(leftSideNode)

        rightSideNode = SKSpriteNode(color: UIColor.blue, size: CGSize(width: 10, height: size.height / 4 * 3 - 20))
        rightSideNode.position = CGPoint(x: size.width / 3 * 2 - 18, y: size.height / 4)
        rightSideNode.zRotation = -CGFloat.pi / 18
        rightSideNode.physicsBody = SKPhysicsBody(rectangleOf: CGSize(width: 10, height: rightSideNode.size.height))
        rightSideNode.physicsBody?.isDynamic = false
        addChild(rightSideNode)
    }

    func updateViewLayout() {
        guard let view = view else { return }

        let maskLayer = CAShapeLayer()
        maskLayer.frame = view.bounds

        let leftPath = UIBezierPath(rect: CGRect(x: 0, y: 0, width: view.size.width / 3 + 1 + leftSideNode.size.width, height: view.size.height * 2))
        rotatePath(leftPath, rotation: -CGFloat.pi / 16)

        let rightPath = UIBezierPath(rect: CGRect(x: view.size.width / 3 * 2 + 2.5 - rightSideNode.size.width, y: 0, width: view.size.width / 3 + rightSideNode.size.width, height: view.size.height * 2))
        rotatePath(rightPath, rotation: CGFloat.pi / 18)

        let path = UIBezierPath(rect: view.bounds)
        path.append(leftPath)
        path.append(rightPath)

        maskLayer.fillRule = CAShapeLayerFillRule.evenOdd
        maskLayer.path = path.cgPath
        view.layer.mask = maskLayer

        colorsView.frame = CGRect(x: 0, y: view.height - CGFloat(liquidSurfaceHeight / 2) /*- 10*/, width: view.size.width, height: CGFloat(liquidSurfaceHeight / 2))
        colorsView.backgroundColor = .clear
    }

    private func rotatePath(_ path: UIBezierPath, rotation: CGFloat) {
        let bounds: CGRect = path.cgPath.boundingBox
        let center = CGPoint(x: bounds.midX, y: bounds.midY)

        var transform: CGAffineTransform = .identity
        transform = transform.translatedBy(x: center.x, y: center.y)
        transform = transform.rotated(by: rotation)
        transform = transform.translatedBy(x: -center.x, y: -center.y)
        path.apply(transform)
    }

    override func update(_ currentTime: TimeInterval) {
        super.update(currentTime)

        if !hasReferenceFrameTime {
            lastFrameTime = currentTime
            hasReferenceFrameTime = true
            return
        }

        let dt: CFTimeInterval = currentTime - lastFrameTime

        var accumilator: CFTimeInterval = 0
        accumilator += dt

        while accumilator >= kFixedTimeStep {
            fixedUpdate(kFixedTimeStep)
            accumilator -= kFixedTimeStep
        }

        fixedUpdate(accumilator)

        lateUpdate(dt)

        lastFrameTime = currentTime
    }

    private func fixedUpdate(_ dt: CFTimeInterval) {
        liquidNode.update(dt)

        for rock in ingredientNodes {
            if rock.isAboveWater && rock.position.y <= liquidNode.position.y + CGFloat(liquidNode.surfaceHeight) {
                rock.isAboveWater = false
                let force = physicsWorld.gravity.dy * 4
                liquidNode.splashAt(x: Float(rock.position.x), force: force, width: splashWidth)
            } else if !rock.isAboveWater && rock.position.y > liquidNode.position.y + CGFloat(liquidNode.surfaceHeight) + CGFloat(colorLayerHeight) {
                rock.isAboveWater = true
            }
        }
    }

    private func lateUpdate(_ dt: CFTimeInterval) {
        liquidNode.render()
    }
}

extension LiquidScene {
    func addColor(color: UIColor) {
        removeColorsView()
        liquidColors.append(color)
        updateColors()
    }

    private func updateColors() {
        let colorLayerHeight = liquidSurfaceHeight / Float(maxColorsCount)

        for (index, color) in liquidColors.enumerated() {
            if index == liquidColors.count - 1 || index == maxColorsCount - 1 {
                break
            }

            let heightRatio: CGFloat = (view?.size.height ?? 0) / size.height
            let colorView = UIView(frame: CGRect(x: 0, y: colorsView.height - CGFloat(colorLayerHeight) * heightRatio * CGFloat(index + 1), width: colorsView.width, height: CGFloat(colorLayerHeight) * heightRatio))
            colorView.backgroundColor = color.withAlphaComponent(0.75)
            colorsView.addSubview(colorView)
        }

        view?.addSubview(colorsView)

        updateLiquidNode()
    }

    private func updateLiquidNode() {
        liquidNode.setColour(liquidColors.last?.withAlphaComponent(0.5))
        liquidHeight = colorLayerHeight
        liquidNode.setLiquidHeight(liquidHeight)

        liquidNode.position.y = (CGFloat(colorLayerHeight)) * CGFloat(colorsUnits - 1)

        updateGravity(units: Float(colorsUnits))
    }

    private func updateGravity(units: Float) {
        gravity.region = SKRegion(size: CGSize(width: size.width, height: CGFloat(colorLayerHeight * units)))
        topCenterNode.position = CGPoint(x: size.width / 2, y: CGFloat(colorLayerHeight * units / 2))
    }

    private func removeColorsView() {
        colorsView.subviews.forEach {
            $0.removeFromSuperview()
        }
        colorsView.removeFromSuperview()
    }

    func addSolid(image: UIImage?) {
        guard let image = image else { return }

        let texture = SKTexture(image: image)
        let newRock = IngredientNode(texture: texture)

        let solidSize = CGSize(width: 25, height: 25)
        let body = SKPhysicsBody(rectangleOf: solidSize)
        body.mass = 0.05
        body.allowsRotation = true
        newRock.physicsBody = body
        body.fieldBitMask = 0x1 << 0
        body.friction = 0.5

        let solidPosition = CGPoint(x: size.width / 2 + CGFloat.random(in: -80 ..< 80), y: size.height / 2)
        newRock.initialPosition = solidPosition
        newRock.position = solidPosition
        newRock.zPosition = 0
        ingredientNodes.append(newRock)
        addChild(newRock)
    }

    func resetContent() {
        removeColorsView()
        liquidColors.removeAll()

        ingredientNodes.forEach { $0.removeFromParent() }
        ingredientNodes.removeAll()

        liquidHeight = 0
        liquidNode.setLiquidHeight(liquidHeight)
        liquidNode.position.y = 0

        updateGravity(units: 0)
    }

    func blendColors(finalColor: UIColor) {
        removeColorsView()

        liquidHeight = colorLayerHeight * Float(colorsUnits)
        liquidNode.setLiquidHeight(liquidHeight)
        liquidNode.position.y = 0

        updateGravity(units: Float(colorsUnits))

        liquidNode.setColour(finalColor.withAlphaComponent(0.5))

        let force = physicsWorld.gravity.dy * 4
        liquidNode.splashAt(x: Float(size.width / 2), force: force, width: splashWidth)
    }
}
