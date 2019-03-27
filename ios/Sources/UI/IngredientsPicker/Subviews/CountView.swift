import UIKit

class CountView: UIView {
    private let countLabel = ComponentFactory.label(for: .H4, fontStyle: .bold, textColor: .white)

    private var count = 0

    init() {
        super.init(frame: .zero)

        backgroundColor = .rouge
        addSubview(countLabel)

        layer.cornerRadius = 10
    }

    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    override func layoutSubviews() {
        super.layoutSubviews()
        countLabel.pin.hCenter().vCenter(-0.5).sizeToFit()
    }

    func configure(count: Int) {
        self.count = count
        isHidden = count < 1
        countLabel.text = "\(count)"
        setNeedsLayout()
    }

    override func sizeThatFits(_ size: CGSize) -> CGSize {
        return CGSize(width: layer.cornerRadius * (CGFloat(count) > 9 ? 3 : 2), height: layer.cornerRadius * 2)
    }
}
