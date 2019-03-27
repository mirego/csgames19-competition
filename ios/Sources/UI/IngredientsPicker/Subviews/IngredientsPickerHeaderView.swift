import UIKit

class IngredientsPickerHeaderView: UIView {
    private let delimiter = UIView()
    private let title = ComponentFactory.label(for: .H2, fontStyle: .semiBold, textColor: .darkGreyBlue)
    private let subTitle = ComponentFactory.label(for: .H3, fontStyle: .regular, textColor: .slateGrey)

    init() {
        super.init(frame: .zero)

        title.text = "Add ingredients"
        addSubview(title)

        subTitle.textAlignment = .center
        subTitle.text = "Tap on an ingredients to add it\nautomatically in the blender"
        subTitle.numberOfLines = 2
        addSubview(subTitle)
        delimiter.backgroundColor = .veryLightPink
        addSubview(delimiter)
    }

    required init(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    override func layoutSubviews() {
        super.layoutSubviews()

        title.pin.top(21).hCenter().sizeToFit()
        subTitle.pin.below(of: title).marginTop(10).horizontally(IngredientsPickerView.sidePadding).sizeToFit(.width)

        delimiter.pin.bottom().horizontally(IngredientsPickerView.sidePadding).height(1)
    }
}
