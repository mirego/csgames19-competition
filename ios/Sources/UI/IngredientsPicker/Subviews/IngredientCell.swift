import UIKit
import Kingfisher

class IngredientCell: UICollectionViewCell {
    static let reuseIdentifier = "IngredientCell"
    static let cellSize = CGSize(width: 70, height: 94)

    private let imageContainer = UIView()
    private let hightlightedView = UIView()

    private let image = UIImageView()
    private let title = ComponentFactory.label(for: .H4, textColor: .darkGreyBlue)
    private let countView = CountView()

    override var isHighlighted: Bool {
        didSet {
            hightlightedView.isHidden = !isHighlighted
        }
    }

    override init(frame: CGRect) {
        super.init(frame: frame)

        backgroundColor = .clear

        imageContainer.backgroundColor = .white
        imageContainer.layer.applySketchShadow(color: .black, alpha: 0.1, x: 0, y: 0, blur: 10, spread: 0)
        contentView.addSubview(imageContainer)

        hightlightedView.isHidden = true
        hightlightedView.backgroundColor = UIColor.darkBlueGrey.withAlphaComponent(0.1)
        imageContainer.addSubview(hightlightedView)

        imageContainer.addSubview(image)
        contentView.addSubview(countView)

        title.textAlignment = .center
        contentView.addSubview(title)
    }

    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    override func layoutSubviews() {
        super.layoutSubviews()
        imageContainer.pin.top().horizontally().height(width)
        imageContainer.layer.cornerRadius = width / 2
        imageContainer.layer.shadowPath = UIBezierPath(roundedRect: imageContainer.bounds, cornerRadius: imageContainer.layer.cornerRadius).cgPath
        hightlightedView.pin.all()
        hightlightedView.layer.cornerRadius = imageContainer.layer.cornerRadius
        image.pin.center().size(44)
        countView.pin.top().right().sizeToFit()
        title.pin.bottom().horizontally().sizeToFit(.width)
    }

    func configure(viewModel: IngredientViewModel) {
        title.text = viewModel.name
        countView.configure(count: viewModel.count)
        image.kf.setImage(with: URL(string: viewModel.imageUrl))
        setNeedsLayout()
    }
}
