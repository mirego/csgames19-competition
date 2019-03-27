import UIKit
import MRGTaylor

protocol IngredientsPickerViewDelegate: AnyObject {
    func didTapCloseButton()
    func didTap(ingredientViewModel: IngredientViewModel)
}

class IngredientsPickerView: UIView {
    static let sidePadding = 16.f

    private let headerView = IngredientsPickerHeaderView()
    private let closeButton = ExtendedButton(type: .custom)
    private let flowLayout = UICollectionViewFlowLayout()
    private let collectionView: UICollectionView
    private let loadingIndicator = UIActivityIndicatorView(style: .whiteLarge)

    private var sections: [IngredientsSectionViewModel] = []

    weak var delegate: IngredientsPickerViewDelegate?

    var isLoading: Bool = false {
        didSet {
            isLoading ? loadingIndicator.startAnimating() : loadingIndicator.stopAnimating()
            collectionView.isHidden = isLoading
        }
    }

    init() {
        collectionView = UICollectionView(frame: .zero, collectionViewLayout: flowLayout)
        super.init(frame: .zero)
        backgroundColor = .white

        addSubview(headerView)

        closeButton.setImage(UIImage(named: "icn-close"), for: .normal)
        closeButton.addAction(events: .touchUpInside) { [unowned self] (_) in
            self.delegate?.didTapCloseButton()
        }
        addSubview(closeButton)

        loadingIndicator.color = .darkGreyBlue
        addSubview(loadingIndicator)

        flowLayout.sectionInset = .margins(IngredientsPickerView.sidePadding)
        flowLayout.minimumLineSpacing = 16
        flowLayout.itemSize = IngredientCell.cellSize
        collectionView.backgroundColor = .clear
        collectionView.register(IngredientCell.self, forCellWithReuseIdentifier: IngredientCell.reuseIdentifier)
        collectionView.register(IngredientSectionTitleView.self, forSupplementaryViewOfKind: UICollectionView.elementKindSectionHeader, withReuseIdentifier: IngredientSectionTitleView.reuseIdentifier)
        collectionView.dataSource = self
        collectionView.delegate = self
        addSubview(collectionView)
    }

    required init(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    override func layoutSubviews() {
        super.layoutSubviews()
        headerView.pin.top().horizontally().height(105)
        closeButton.pin.top(IngredientsPickerView.sidePadding).right(IngredientsPickerView.sidePadding).sizeToFit()
        collectionView.pin.below(of: headerView).horizontally().bottom()
        loadingIndicator.pin.hCenter().vCenter((headerView.height - safeAreaInsets.bottom) / 2)
    }

    func configure(sections: [IngredientsSectionViewModel]) {
        self.sections = sections
        collectionView.reloadData()
    }
}

extension IngredientsPickerView: UICollectionViewDataSource {
    func numberOfSections(in collectionView: UICollectionView) -> Int {
        return sections.count
    }

    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return sections[section].ingredientViewModels.count
    }

    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: IngredientCell.reuseIdentifier, for: indexPath) as! IngredientCell
        cell.configure(viewModel: sections[indexPath.section].ingredientViewModels[indexPath.row])
        return cell
    }

    func collectionView(_ collectionView: UICollectionView, viewForSupplementaryElementOfKind kind: String, at indexPath: IndexPath) -> UICollectionReusableView {
        let headerView = collectionView.dequeueReusableSupplementaryView(ofKind: UICollectionView.elementKindSectionHeader, withReuseIdentifier: IngredientSectionTitleView.reuseIdentifier, for: indexPath) as! IngredientSectionTitleView
        headerView.configure(title: sections[indexPath.section].title, showDelimiter: indexPath.section != 0)
        return headerView
    }
}

extension IngredientsPickerView: UICollectionViewDelegate, UICollectionViewDelegateFlowLayout {
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, referenceSizeForHeaderInSection section: Int) -> CGSize {
        return CGSize(width: collectionView.width, height: 51)
    }

    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        delegate?.didTap(ingredientViewModel: sections[indexPath.section].ingredientViewModels[indexPath.row])
    }
}
