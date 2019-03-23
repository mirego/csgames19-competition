import UIKit

class PushButton: UIButton {
    private let grayRoundView = UIView()
    private let redRoundView = UIView()
    private let pushLabel = ComponentFactory.label(for: .H5, fontStyle: .bold, textColor: .white)

    override var isHighlighted: Bool {
        didSet {
            redRoundView.backgroundColor = isHighlighted ? .grapefruitHighlighted : .grapefruit
        }
    }

    override var isEnabled: Bool {
        didSet {
            alpha = isEnabled ? 1 : 0.6
        }
    }

    init() {
        super.init(frame: .zero)

        grayRoundView.isUserInteractionEnabled = false
        grayRoundView.backgroundColor = .slateGrey
        addSubview(grayRoundView)

        redRoundView.isUserInteractionEnabled = false
        redRoundView.backgroundColor = .grapefruit
        addSubview(redRoundView)

        pushLabel.text = "Push"
        addSubview(pushLabel)
        
        size = CGSize(width: 56, height: 56)
    }

    required init(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    override func layoutSubviews() {
        super.layoutSubviews()

        grayRoundView.pin.all()
        grayRoundView.layer.cornerRadius = grayRoundView.width / 2

        redRoundView.pin.center().size(46)
        redRoundView.layer.cornerRadius = redRoundView.width / 2

        pushLabel.pin.center().sizeToFit()
    }
}
