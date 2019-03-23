import UIKit

class GenericButton: UIButton {
    enum Style {
        case light
        case dark

        var backgroundColor: UIColor {
            switch self {
            case .light:
                return .white
            case .dark:
                return .darkGreyBlue
            }
        }

        var tintColor: UIColor {
            switch self {
            case .light:
                return .darkGreyBlue
            case .dark:
                return .white
            }
        }
    }

    private let verticalPadding = 15.f
    private let iconTextMargin = 8.f

    private let hightlightedView = UIView()

    override var isHighlighted: Bool {
        didSet {
            hightlightedView.isHidden = !isHighlighted
        }
    }

    override var isEnabled: Bool {
        didSet {
            alpha = isEnabled ? 1 : 0.6
        }
    }

    init(icon: UIImage?, text: String?, style: Style) {
        super.init(frame: .zero)

        backgroundColor = style.backgroundColor
        layer.cornerRadius = 6
        layer.applySketchShadow(color: .black, alpha: 0.3, x: 0, y: 2, blur: 5, spread: 0)
        tintColor = style.tintColor
        titleLabel?.font = Headline.H4.font(withStyle: .bold)

        setImage(icon, for: .normal)
        setTitle(text, for: .normal)
        setTitleColor(style.tintColor, for: .normal)

        hightlightedView.isHidden = true
        hightlightedView.backgroundColor = style.tintColor.withAlphaComponent(0.1)
        hightlightedView.layer.cornerRadius = layer.cornerRadius
        insertSubview(hightlightedView, at: 0)
    }
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    override func layoutSubviews() {
        super.layoutSubviews()
        hightlightedView.pin.all()
        layer.applySketchShadowPath()

        guard let imageView = imageView, let titleLabel = titleLabel else { return }
        imageView.sizeToFit()
        titleLabel.sizeToFit()
        let sideMargin = (width - imageView.width - titleLabel.width - iconTextMargin) / 2
        imageView.pin.left(sideMargin).vCenter()
        titleLabel.pin.right(sideMargin).vCenter()
    }
}
