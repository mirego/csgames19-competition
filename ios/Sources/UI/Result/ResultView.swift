import UIKit
import Cheers

protocol ResultViewDelegate: AnyObject {
    func didTapNewDrink()
}

class ResultView: UIView {
    private let leftBackground = UIImageView(image: UIImage(named: "bg_result_left"))
    private let rightBackground = UIImageView(image: UIImage(named: "bg_result_right"))

    private let loadingIndicator = UIActivityIndicatorView(style: .whiteLarge)
    private let contentView = UIView()
    private let title = UILabel()
    private let rating = UILabel()
    private let subTitle = UILabel()

    private let tasteTitle = ComponentFactory.label(for: .H3, fontStyle: .bold, textColor: .darkBlueGrey)
    private let tasteProgress = ProgressView(color: .topaz)

    private let volumeTitle = ComponentFactory.label(for: .H3, fontStyle: .bold, textColor: .darkBlueGrey)
    private let volumeProgress = ProgressView(color: .dullYellow)

    private let strengthTitle = ComponentFactory.label(for: .H3, fontStyle: .bold, textColor: .darkBlueGrey)
    private let strengthProgress = ProgressView(color: .darkPeach)

    private let newDrinkButton = GenericButton(icon: nil, text: "New drink", style: .dark)

    private let cheerView = CheerView()

    weak var delegate: ResultViewDelegate?

    var isLoading: Bool = false {
        didSet {
            isLoading ? loadingIndicator.startAnimating() : loadingIndicator.stopAnimating()
            contentView.isHidden = isLoading
        }
    }

    var isViewClosed = false

    init() {
        super.init(frame: .zero)
        backgroundColor = .white

        addSubview(leftBackground)
        addSubview(rightBackground)

        loadingIndicator.color = .darkGreyBlue
        addSubview(loadingIndicator)

        contentView.alpha = 0
        addSubview(contentView)

        title.text = "Our rating"
        title.font = FontStyle.light.font(withSize: 28)
        title.textColor = .darkGreyBlue
        contentView.addSubview(title)

        rating.textAlignment = .center
        rating.text = "0%"
        rating.font = FontStyle.bold.font(withSize: 70)
        rating.textColor = .darkGreyBlue
        contentView.addSubview(rating)

        subTitle.font = FontStyle.regular.font(withSize: 17)
        subTitle.textColor = .darkGreyBlue
        subTitle.numberOfLines = 2
        subTitle.textAlignment = .center
        contentView.addSubview(subTitle)

        tasteTitle.text = "👌🏻 Taste"
        contentView.addSubview(tasteTitle)
        contentView.addSubview(tasteProgress)

        volumeTitle.text = "⚗️ Volume"
        contentView.addSubview(volumeTitle)
        contentView.addSubview(volumeProgress)

        strengthTitle.text = "💪 Strength"
        contentView.addSubview(strengthTitle)
        contentView.addSubview(strengthProgress)

        addSubview(newDrinkButton)
        newDrinkButton.addControlEvent(.touchUpInside) { [weak self] (control: UIControl) in
            self?.delegate?.didTapNewDrink()
        }

        cheerView.config.particle = .confetti(allowedShapes: [.rectangle])
        addSubview(cheerView)
    }

    required init(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    override func layoutSubviews() {
        super.layoutSubviews()
        leftBackground.pin.top().left()
        rightBackground.pin.bottom().right()
        loadingIndicator.pin.center()

        newDrinkButton.pin.bottom(32).hCenter().size(CGSize(width: 164, height: 48))
        contentView.pin.top().above(of: newDrinkButton).horizontally()

        title.pin.hCenter().top(26).sizeToFit()
        rating.pin.below(of: title).marginTop(-10).horizontally().sizeToFit(.width)
        subTitle.pin.below(of: rating).marginTop(20).horizontally(20).sizeToFit(.width)

        let middlePartWidth = 229.f
        let sideMargin = (width - middlePartWidth) / 2
        tasteTitle.pin.below(of: subTitle).marginTop(29).left(sideMargin).sizeToFit()
        tasteProgress.pin.below(of: tasteTitle, aligned: .left).marginTop(15).width(middlePartWidth).height(14)

        volumeTitle.pin.below(of: tasteProgress).marginTop(29).left(sideMargin).sizeToFit()
        volumeProgress.pin.below(of: volumeTitle, aligned: .left).marginTop(15).width(middlePartWidth).height(14)

        strengthTitle.pin.below(of: volumeProgress).marginTop(29).left(sideMargin).sizeToFit()
        strengthProgress.pin.below(of: strengthTitle, aligned: .left).marginTop(15).width(middlePartWidth).height(14)

        cheerView.pin.all()
    }

    func configure(viewModel: ResultViewModel) {
        subTitle.text = viewModel.subTitle
        tasteProgress.configure(progress: viewModel.tasteResult)
        volumeProgress.configure(progress: viewModel.volumeResult)
        strengthProgress.configure(progress: viewModel.strengthResult)

        UIView.animate(withDuration: 0.2) {
            self.contentView.alpha = 1
        }

        UIView.animate(withDuration: 0.2, animations: {
            self.contentView.alpha = 1
        }) { (_) in
            self.startRatingAnimation(finalRating: viewModel.grade, nextRating: 0)
        }
        setNeedsLayout()

        if viewModel.grade == 100 {
            delay(1.5) { [weak self] in
                self?.cheerView.start()
            }
        }
    }

    func closeView() {
        cheerView.stop()
        isViewClosed = true
    }

    private func startRatingAnimation(finalRating: Int, nextRating: Int) {
        guard !isViewClosed else { return }
        rating.text = "\(nextRating)%"
        if nextRating < finalRating {
            let duration = 0.5 / TimeInterval(finalRating)
            Timer.scheduledTimer(withTimeInterval: duration, repeats: false) { [weak self] (_) in
                self?.startRatingAnimation(finalRating: finalRating, nextRating: min(nextRating + 1, finalRating))
            }
        }
    }
}
