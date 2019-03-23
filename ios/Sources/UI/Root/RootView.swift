import UIKit
import PinLayout
import ActionKit

protocol RootViewDelegate: AnyObject {
    func didTapAddIngredient()
    func didTapServe()
}

class RootView: UIView {
    private let backgroundView = UIImageView(image: UIImage(named: "splash-bg"))

    private let sunImage = UIImageView(image: UIImage(named: "sun"))
    private let cloudLeftImage = UIImageView(image: UIImage(named: "cloud-left"))
    private let cloudRightImage = UIImageView(image: UIImage(named: "cloud-right"))
    private let palmTreeImage = UIImageView(image: UIImage(named: "palm-tree"))

    private let bottomView = UIView()
    private let addIngredientButton = GenericButton(icon: UIImage(named: "icn-add"), text: "Add Ingredients", style: .light)
    private let serveButton = GenericButton(icon: UIImage(named: "umbrella"), text: "Serve !", style: .light)

    private let bird1 = UIImageView(image: UIImage(named: "bird_1"))
    private let bird2 = UIImageView(image: UIImage(named: "bird_2"))
    private let bird3 = UIImageView(image: UIImage(named: "bird_3"))
    private let bird4 = UIImageView(image: UIImage(named: "bird_4"))
    private let bird5 = UIImageView(image: UIImage(named: "bird_5"))
    private let bird6 = UIImageView(image: UIImage(named: "bird_6"))

    private let blender = UIImageView(image: UIImage(named: "blender"))
    private let blenderGlass = UIImageView(image: UIImage(named: "blender_glass"))
    private let pushButton = PushButton()

    private let scene = LiquidScene(fileNamed: "GameScene")
    private let liquidSceneView: SKView =  {
        let skView = SKView()
        skView.ignoresSiblingOrder = true
        return skView
    }()

    weak var delegate: RootViewDelegate?

    init() {
        super.init(frame: .zero)

        backgroundView.contentMode = .scaleAspectFill
        addSubview(backgroundView)

        addSubview(sunImage)
        addSubview(cloudLeftImage)
        addSubview(cloudRightImage)

        addSubview(palmTreeImage)

        bottomView.backgroundColor = .darkGreyBlue
        addSubview(bottomView)

        addSubview(bird1)
        addSubview(bird2)
        addSubview(bird3)
        addSubview(bird4)
        addSubview(bird5)
        addSubview(bird6)

        liquidSceneView.backgroundColor = .clear
        addSubview(liquidSceneView)

        scene?.scaleMode = .aspectFill
        liquidSceneView.presentScene(scene)

        blenderGlass.alpha = 0.2
        addSubview(blenderGlass)
        addSubview(blender)
        pushButton.isEnabled = false
        addSubview(pushButton)

        bottomView.addSubview(addIngredientButton)
        addIngredientButton.addControlEvent(.touchUpInside) { [weak self] (control: UIControl) in
            self?.delegate?.didTapAddIngredient()
        }

        pushButton.addControlEvent(.touchUpInside) { [weak self] (control: UIControl) in
            self?.addIngredientButton.isEnabled = false
            self?.serveButton.isEnabled = true
            self?.scene?.blendColors(finalColor: UIColor.purple)
        }

        serveButton.isEnabled = false
        bottomView.addSubview(serveButton)
        serveButton.addControlEvent(.touchUpInside) { [weak self] (control: UIControl) in
            self?.delegate?.didTapServe()
        }
    }

    required init(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    override func layoutSubviews() {
        super.layoutSubviews()
        backgroundView.pin.all()
        sunImage.pin.top().right(12)
        cloudLeftImage.pin.top().left()
        cloudRightImage.pin.top().right()
        palmTreeImage.pin.bottom().horizontally().sizeToFit(.width)

        let sideMargin = 16.f
        let buttonWidth = (width - sideMargin * 2.5) / 2
        bottomView.pin.bottom().horizontally().height(155 + safeAreaInsets.bottom)
        addIngredientButton.pin.bottom(23 + safeAreaInsets.bottom).left(sideMargin).size(CGSize(width: buttonWidth, height: 48))
        serveButton.pin.bottom(23 + safeAreaInsets.bottom).right(sideMargin).size(CGSize(width: buttonWidth, height: 48))

        let blenderOffset = -54.f
        blender.pin.above(of: bottomView, aligned: .center).marginBottom(blenderOffset)
        blenderGlass.pin.above(of: bottomView, aligned: .center).marginBottom(blenderOffset + 146)
        liquidSceneView.pin.below(of: blender).marginTop(-blender.height * 1.33).hCenter().size(CGSize(width: width, height: blender.height))

        let sc = liquidSceneView.scene as? LiquidScene
        sc?.updateViewLayout()

        pushButton.pin.hCenter().bottom(67 + bottomView.height + blenderOffset)

        bird1.pin.below(of: cloudLeftImage).left(11).marginTop(25)
        bird2.pin.left(of: cloudRightImage, aligned: .bottom).marginRight(13).marginBottom(-35)
        bird3.pin.above(of: blender, aligned: .left).marginBottom(55).marginLeft(62)
        bird4.pin.above(of: blender, aligned: .right).marginBottom(50).marginRight(30)
        bird5.pin.above(of: blender, aligned: .left).marginLeft(-2)
        bird6.pin.right(of: blender, aligned: .top).marginTop(100)
    }

    func addIngredient(/* TODO Add a incredient!*/) {
        pushButton.isEnabled = true
        
        // Here
        let url = URL(string: "https://mirego-csgames19.herokuapp.com/ingredients")!
        
        var request = URLRequest(url: url)
        request.httpMethod = "GET"
        request.setValue("Carleton", forHTTPHeaderField: "Team")
        
        let session = URLSession.shared
        
        let task = session.dataTask(with: request) { data, response, error in
            if let json = try? JSONSerialization.jsonObject(with: data!, options: []) {
                print(json)
            }
        }
        
        task.resume()

        // Use scene?.addColor(color: color) to add a liquid color layer
        // Use scene?.addSolid(image: image) to add a solid (ice, sugar, basil, etc...)
        scene?.addColor(color: UIColor.red)
    }

    func resetBlender() {
        scene?.resetContent()
        addIngredientButton.isEnabled = true
        pushButton.isEnabled = false
        serveButton.isEnabled = false
    }
}
