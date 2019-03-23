#if os(OSX)
import AppKit
#else
import UIKit
#endif


/// The view to show particles
open class CheerView: UIView {
  open var config = Config()
  var emitter: CAEmitterLayer?

  #if os(OSX)
  open override func viewDidMoveToSuperview() {
    super.viewDidMoveToSuperview()
    
    wantsLayer = true
  }
  #else
  open override func didMoveToSuperview() {
    super.didMoveToSuperview()
    
    isUserInteractionEnabled = false
  }
  #endif

  /// Start animation
  open func start() {
    stop()

    var yOrigin: CGFloat = 0
    var yMultiplier: CGFloat = 1
    #if os(OSX)
    yOrigin = bounds.height
    yMultiplier = -1
    #endif
    
    let emitter = CAEmitterLayer()
    emitter.emitterPosition = CGPoint(x: bounds.width / 2, y: yOrigin)
    emitter.emitterShape = CAEmitterLayerEmitterShape.line
    emitter.emitterSize = CGSize(width: bounds.width, height: 1)
    emitter.renderMode = CAEmitterLayerRenderMode.additive

    // This combination will ensure that all color/image combinations are evenly distributed.
    // For example, if you have only one color, then we still want to make sure
    // that all "allowed" particle types are represented in the result.
    let combinations = Array<(UIColor, UIImage)>.createAllCombinations(
      from: config.colors,
      and: pickImages()
    )

    let cells: [CAEmitterCell] = combinations.reduce([]) { (accum, combination) in
      let cell = CAEmitterCell()
      cell.birthRate = 20
      cell.lifetime = 20.0
      cell.lifetimeRange = 10
      cell.velocity = 250 * yMultiplier
      cell.velocityRange = 50
      cell.emissionLongitude = CGFloat.pi
      cell.emissionRange = CGFloat.pi * 0.2
      cell.spinRange = 5
      cell.scale = 0.3
      cell.scaleRange = 0.2
      cell.color = combination.0.cgColor
      cell.alphaSpeed = -0.1
      cell.contents = combination.1.cgImage
      cell.xAcceleration = 20
      cell.yAcceleration = 50 * yMultiplier
      cell.redRange = config.colorRange
      cell.greenRange = config.colorRange
      cell.blueRange = config.colorRange

      return accum + [cell]
    }

    emitter.emitterCells = cells
    emitter.beginTime = CACurrentMediaTime()

    config.customize?(cells)

    let rootLayer: CALayer? = layer
    rootLayer?.addSublayer(emitter)
    
    self.emitter = emitter
  }

  /// Stop animation
  open func stop() {
    emitter?.birthRate = 0
  }

  func pickImages() -> [UIImage] {
    let generator = ImageGenerator()

    switch config.particle {
    case .confetti(let allowedShapes):
        return allowedShapes
          .map { generator.confetti(shape: $0) }
          .compactMap({ $0 })
    case .image(let images):
      return images
    case .text(let size, let strings):
      return strings.compactMap({ generator.generate(size: size, string: $0) })
    }
  }
}
