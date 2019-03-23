import UIKit
import MRGTaylor

protocol IngredientsPickerViewDelegate: AnyObject {
    func didTapCloseButton()
}

class IngredientsPickerView: UIView {
    static let sidePadding = 16.f

    private let closeButton = ExtendedButton(type: .custom)
    private let loadingIndicator = UIActivityIndicatorView(style: .whiteLarge)

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
    }

    required init(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    override func layoutSubviews() {
        super.layoutSubviews()
        closeButton.pin.top(IngredientsPickerView.sidePadding).right(IngredientsPickerView.sidePadding).sizeToFit()
        loadingIndicator.pin.center()
    }
}
