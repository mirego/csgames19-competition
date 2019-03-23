import UIKit
import MRGTaylor

protocol IngredientsPickerViewDelegate: AnyObject {
    func didTapCloseButton()
}

class IngredientsPickerView: UIView {
    static let sidePadding = 16.f

    private let closeButton = ExtendedButton(type: .custom)
    private let loadingIndicator = UIActivityIndicatorView(style: .whiteLarge)

    private let juiceHeader: TitleLabel = TitleLabel(title: "Juice")
    private let drinkHeader: TitleLabel = TitleLabel(title: "Soft Drink")
    private let ingredientHeader: TitleLabel = TitleLabel(title: "Ingredient")
    private let alchoolHeader: TitleLabel = TitleLabel(title: "Alcohol")

    private var ingredients: [Ingredient]?

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

        self.addSubview(self.juiceHeader)
        self.addSubview(self.drinkHeader)
        self.addSubview(self.ingredientHeader)
        self.addSubview(self.alchoolHeader)
    }

    required init(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    override func layoutSubviews() {
        super.layoutSubviews()
        closeButton.pin.top(IngredientsPickerView.sidePadding).right(IngredientsPickerView.sidePadding).sizeToFit()
        loadingIndicator.pin.center()
    }

    func configure(ingredients: Ingredients) {
        self.ingredients = ingredients
        // todo update ui
    }
}
