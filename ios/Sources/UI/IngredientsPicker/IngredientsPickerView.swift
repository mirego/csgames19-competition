import UIKit
import MRGTaylor
import Alamofire
import AlamofireImage

protocol IngredientsPickerViewDelegate: AnyObject {
    func didTapCloseButton()
}

class IngredientsPickerView: UIView {
    static let sidePadding = 16.f

    private let closeButton = ExtendedButton(type: .custom)
    private let loadingIndicator = UIActivityIndicatorView(style: .whiteLarge)

    var scrollView: UIScrollView!
    
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
        if(scrollView != nil) {
            scrollView.sizeToFit()
        }
    }
    
    func setIngredients(data: NSDictionary) {
        
        let width = frame.width
        
        scrollView = UIScrollView(frame: .zero)
        scrollView.frame.size.width = frame.width
        scrollView.frame.size.height = frame.height - 40
        scrollView.frame.origin.y = 40
        
        let alcohols = data["alcohols"]!
        let alcoholsView = IngredientListView("Alcohols", width: width, data:alcohols as! NSArray)
        scrollView.addSubview(alcoholsView)
        
        let drinks = data["drinks"]!
        let drinksView = IngredientListView("Drink", width: width, data:drinks as! NSArray)
        drinksView.frame.y = drinksView.frame.y + drinksView.frame.height // YES THIS IS A HACK SUE ME
        scrollView.addSubview(drinksView)
        
        let ingre = data["ingredients"]!
        let ingreView = IngredientListView("Ingredients", width: width, data:ingre as! NSArray)
        ingreView.frame.y = drinksView.frame.y + drinksView.frame.height
        scrollView.addSubview(ingreView)
        
        let juices = data["juices"]!
        let juicesView = IngredientListView("Juices", width: width, data:juices as! NSArray)
        juicesView.frame.y = ingreView.frame.y + ingreView.frame.height
        scrollView.addSubview(juicesView)
        
        scrollView.contentSize = CGSize(width: width, height: juicesView.frame.y + juicesView.frame.height)
        addSubview(scrollView)
    }

}

/**
 * View for a collection of ingredients
 */
class IngredientListView : UIView {
    
    init(_ label: String, width: CGFloat, data:NSArray) {
        super.init(frame: .zero)
        frame.size.width = width
        frame.size.height = (width / 4) * CGFloat(data.count / 4) + 50
        
        let labelView = UILabel()
        labelView.height = 50
        labelView.width = width
        labelView.x = 0
        labelView.y = 0
        labelView.textColor = UIColor.black
        addSubview(labelView)
        
        DispatchQueue.main.async {
            labelView.text = label
        }
        
        var i = 0;
        for item in data {
            addIngredient(item: item as! NSDictionary, index: i)
            i += 1
        }
        
    }
    
    func addIngredient(item:NSDictionary, index:Int) {
        let itemWidth = frame.width / 4
        let position = CGPoint(x: CGFloat(index % 4) * itemWidth, y: CGFloat(index / 4) * itemWidth + 50)
        let ingredientView = IngredientItemView(item: item, width: itemWidth, position: position)
        addSubview(ingredientView)
    }
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
}

/**
 * View for an individual ingredient item
 */
class IngredientItemView : UIView {
    
    var item:NSDictionary
    
    private var imageView:UIImageView
    
    init(item:NSDictionary, width:CGFloat, position:CGPoint) {
        self.item = item
        imageView = UIImageView()
        super.init(frame: .zero)

        // print(item)
        
        frame.size.width = width
        frame.size.height = width
        frame.origin = position
        
        imageView.width = 60
        imageView.height = 60
        imageView.frame.origin = CGPoint(x: width / 2 - 30, y: height / 2 - 30) // Not magic numbers pls don't hate me
        
        addSubview(imageView)
        
        getImage()
        
        self.layer.cornerRadius = width / 2
        layer.backgroundColor = UIColor.white.cgColor
        layer.shadowColor = UIColor.black.cgColor
        layer.shadowOpacity = 0.2
        layer.shadowOffset = CGSize(width: 1, height: 0)
        layer.shadowRadius = 3
    
    }
    
    
    
    required init(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    private func getImage() {
        Alamofire.request(self.item["imageUrl"] as! String).responseImage { response in
            if let image = response.result.value {
                self.imageView.image = image
            }
        }
    }
    
}
