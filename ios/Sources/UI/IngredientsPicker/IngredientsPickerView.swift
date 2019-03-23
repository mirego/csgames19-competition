import UIKit
import MRGTaylor
import PinLayout

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

    private let tableView = UITableView()

    private var ingredients: Ingredients?

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

        self.addSubview(self.juiceHeader)
        self.addSubview(self.drinkHeader)
        self.addSubview(self.ingredientHeader)
        self.addSubview(self.alchoolHeader)

        self.addSubview(self.tableView)
        self.tableView.delegate = self
        self.tableView.dataSource = self
        self.tableView.register(UITableViewCell.self, forCellReuseIdentifier: "cell")

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

        self.tableView.pin.top(40).left(0).right(0).bottom(0)

    }

    func configure(ingredients: Ingredients) {
        self.ingredients = ingredients
        self.tableView.reloadData()
    }
}

extension IngredientsPickerView: UITableViewDelegate, UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        guard self.ingredients != nil else {
            return 0
        }

        switch section {
        case 0:
            return self.ingredients!.juices.count
        case 1:
            return self.ingredients!.drinks.count
        case 2:
            return self.ingredients!.ingredients.count
        case 3:
            return self.ingredients!.alcohols.count
        default:
            return 0
        }
    }

    func numberOfSections(in tableView: UITableView) -> Int {
        return 4
    }

    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell: UITableViewCell = self.tableView.dequeueReusableCell(withIdentifier: "cell", for: indexPath)


        switch indexPath.section {
        case 0:
            cell.textLabel?.text = self.ingredients!.juices[indexPath.row].label
            let url = URL(string: self.ingredients!.juices[indexPath.row].imageUrl)!
            let imageData = NSData(contentsOf: url)!
            cell.imageView?.image = UIImage(data: imageData as Data)
        case 1:
            cell.textLabel?.text = self.ingredients!.drinks[indexPath.row].label
            let url = URL(string: self.ingredients!.drinks[indexPath.row].imageUrl)!
            let imageData = NSData(contentsOf: url)!
            cell.imageView?.image = UIImage(data: imageData as Data)
        case 2:
            cell.textLabel?.text = self.ingredients!.ingredients[indexPath.row].label
            let url = URL(string: self.ingredients!.ingredients[indexPath.row].imageUrl)!
            let imageData = NSData(contentsOf: url)!
            cell.imageView?.image = UIImage(data: imageData as Data)
        case 3:
            cell.textLabel?.text = self.ingredients!.alcohols[indexPath.row].label
            let url = URL(string: self.ingredients!.alcohols[indexPath.row].imageUrl)!
            let imageData = NSData(contentsOf: url)!
            cell.imageView?.image = UIImage(data: imageData as Data)
        default:
            return UITableViewCell()
        }
        
        return cell
    }
}
