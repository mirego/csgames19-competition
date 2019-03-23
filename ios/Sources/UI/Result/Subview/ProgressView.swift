import UIKit

class ProgressView: UIView {

    private let backgroundLayer = CAShapeLayer()
    private let progressLayer = CAShapeLayer()

    init(color: UIColor) {
        super.init(frame: .zero)

        backgroundLayer.fillColor = UIColor.lightWhite.cgColor
        layer.addSublayer(backgroundLayer)

        progressLayer.fillColor = color.cgColor
        layer.addSublayer(progressLayer)
    }

    required init(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    override func layoutSubviews() {
        super.layoutSubviews()

        backgroundLayer.path = UIBezierPath(roundedRect: bounds, cornerRadius: height / 2).cgPath
        backgroundLayer.pin.all()
        progressLayer.pin.all()
    }

    func configure(progress: Int) {
        let startRect = CGRect(x: 0, y: 0, width: 1, height: height)
        let finalRect = CGRect(x: 0, y: 0, width: width * CGFloat(progress) / 100.f, height: height)
        progressLayer.path = UIBezierPath(roundedRect: finalRect, cornerRadius: height / 2).cgPath
        /*
        let animation = CABasicAnimation(keyPath: "path")
        animation.fromValue = UIBezierPath(roundedRect: startRect, cornerRadius: height / 2).cgPath
        animation.toValue = progressLayer.path
        animation.duration = 1
        animation.timingFunction = CAMediaTimingFunction(name: .easeOut)
        animation.isRemovedOnCompletion = false
        progressLayer.add(animation, forKey: animation.keyPath)*/
    }
}
