import UIKit
import MRGTaylor

protocol IngredientsPickerViewDelegate: AnyObject {
    func didTapCloseButton()
}

class IngredientsPickerView: UIView {
    static let sidePadding = 16.f

    private let closeButton = ExtendedButton(type: .custom)
    private let loadingIndicator = UIActivityIndicatorView(style: .whiteLarge)
    private let label = ComponentFactory.label(for: .H2)
    private let sublabel = ComponentFactory.label(for: .H4)
    private let juiceView = UITableView()

    weak var delegate: IngredientsPickerViewDelegate?

    var isLoading: Bool = false {
        didSet {
            isLoading ? loadingIndicator.startAnimating() : loadingIndicator.stopAnimating()
        }
    }

    init() {
        super.init(frame: .zero)
        backgroundColor = .white

        closeButton.setImage(UIImage(named: "icn-close"), for: .normal)
        closeButton.addAction(events: .touchUpInside) { [unowned self] (_) in
            self.delegate?.didTapCloseButton()
        }
        addSubview(closeButton)

        loadingIndicator.color = .darkGreyBlue
        addSubview(loadingIndicator)
        
        label.text = "Add ingredients"
        label.textAlignment = .center
        addSubview(label)
        
        sublabel.text = "Tap on an ingredient to add it automatically in the blender"
        sublabel.numberOfLines = 2
        sublabel.textAlignment = .center
        addSubview(sublabel)
    }

    required init(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    override func layoutSubviews() {
        super.layoutSubviews()
    closeButton.pin.top(IngredientsPickerView.sidePadding).right(IngredientsPickerView.sidePadding).sizeToFit()
        loadingIndicator.pin.center()
        label.pin.top(IngredientsPickerView.sidePadding).center().sizeToFit()
        sublabel.pin.below(of: label).center().size(300)
        
    }
}
