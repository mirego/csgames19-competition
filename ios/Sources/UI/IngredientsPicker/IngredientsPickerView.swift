import UIKit
import MRGTaylor

protocol IngredientsPickerViewDelegate: AnyObject {
    func didTapCloseButton()
    func addIngredient(juice: Juice?)
}


class IngredientsPickerView: UIView, UICollectionViewDelegate, UICollectionViewDataSource, UICollectionViewDelegateFlowLayout, IngredientsPickerViewControllerDelegate {
    
    func addIngredient(juice: Juice) {
        self.delegate?.addIngredient(juice: juice)
    }
    
    func numberOfSections(in collectionView: UICollectionView) -> Int {
        return 2
    }
    
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        switch section {
        case 0:
            return dataSource?.juices.count ?? 0
        case 1:
            return dataSource?.drinks.count ?? 0
        default:
            break
        }
        return 0
    }
    
    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        self.delegate?.addIngredient(juice: dataSource?.juices[indexPath.row])
        self.delegate?.didTapCloseButton()
    }

    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "cell", for: indexPath) as! PickerCell
        switch indexPath.section {
        case 0:
             let data = dataSource?.juices[indexPath.row]
             cell.configureCell(from: data)
             return cell
        case 1:
            let data = dataSource?.drinks[indexPath.row]
            cell.confid(from: data)
            return cell
        default:
            break
        }
        return UICollectionViewCell()
    }
    
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemAt indexPath: IndexPath) -> CGSize {
        return CGSize(width: 70, height: 70)
    }
    
    
    static let sidePadding = 16.f

    private let closeButton = ExtendedButton(type: .custom)
    private let loadingIndicator = UIActivityIndicatorView(style: .whiteLarge)
    
    let collectionView = UICollectionView(frame: .zero, collectionViewLayout: UICollectionViewFlowLayout())
    
    var dataSource: Model?
    weak var delegate: IngredientsPickerViewDelegate?
    
    var isLoading: Bool = false {
        didSet {
            isLoading ? loadingIndicator.startAnimating() : loadingIndicator.stopAnimating()
        }
    }
    
    init() {
        super.init(frame: .zero)
        NetworkServices.sharedInstance().getIngeredients() { [weak self] dataSource in
            self?.dataSource = dataSource
            DispatchQueue.main.async {
                self?.collectionView.reloadData()
                self?.isLoading = false
            }
        }
        
        backgroundColor = .white
        addSubview(collectionView)
        
        collectionView.delegate = self as? UICollectionViewDelegate
        collectionView.dataSource = self as? UICollectionViewDataSource
        collectionView.register(PickerCell.self, forCellWithReuseIdentifier: "cell")
        collectionView.backgroundColor = .clear
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
        collectionView.pin.all()
        closeButton.pin.top(IngredientsPickerView.sidePadding).right(IngredientsPickerView.sidePadding).sizeToFit()
        loadingIndicator.pin.center()
    }
}
