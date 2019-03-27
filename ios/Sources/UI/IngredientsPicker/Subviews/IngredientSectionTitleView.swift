import UIKit

class IngredientSectionTitleView: UICollectionReusableView {
    static let reuseIdentifier = "IngredientSectionTitleView"

    private let delimiter = UIView()
    private let label = ComponentFactory.label(for: .H1, fontStyle: .bold, textColor: .darkGreyBlue)

    override init(frame: CGRect) {
        super.init(frame: frame)

        delimiter.backgroundColor = .veryLightPink
        addSubview(delimiter)
        addSubview(label)
    }

    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    override func layoutSubviews() {
        super.layoutSubviews()
        delimiter.pin.top().horizontally(IngredientsPickerView.sidePadding).height(1)
        label.pin.bottom().horizontally(IngredientsPickerView.sidePadding).sizeToFit()
    }

    func configure(title: String?, showDelimiter: Bool) {
        delimiter.isHidden = !showDelimiter
        label.text = title
        setNeedsLayout()
    }
}
