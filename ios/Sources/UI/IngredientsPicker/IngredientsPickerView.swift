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
    
    var data: APIData? {
        didSet {
            self.layoutSubviews()
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
        
        
        
        if(self.data != nil) {
            //we have the data
            let label = UILabel()
            label.text = "Juice"
            self.addSubview(label)
            label.pin.top(IngredientsPickerView.sidePadding).left(IngredientsPickerView.sidePadding).sizeToFit()
            
            var completedJuiceButtons: [UIButton] = [UIButton]()
            
            for i in 0...self.data!.juices.count-1 {
                let juice = self.data!.juices[i]
                let button = UIButton()
                let buttonLabel = UILabel()
                buttonLabel.text = juice.label
                
                
                let fm = FileManager.default
                do {
                    let documentDirectory = try fm.url(for: .documentDirectory, in: .userDomainMask, appropriateFor:nil, create:false)
                    let imgURL = documentDirectory.appendingPathComponent("\(juice.id)@3x.png")
                    button.setImage(UIImage(contentsOfFile: imgURL.path), for: .normal)
                    button.addAction(events: .touchUpInside) { (button) in
                        //todo fill
                    }
                    self.addSubview(button)
                    //self.addSubview(buttonLabel)
                    
                    if(i == 0) {
                        //first button
                        button.pin.below(of: label).left(IngredientsPickerView.sidePadding).sizeToFit()
                        //buttonLabel.pin.below(of: button).sizeToFit()
                        //completedLabels.append(buttonLabel)
                        completedJuiceButtons.append(button)
                    } else {
                        //not the first button, they should be pinned to the label from above
                        button.pin.below(of: completedJuiceButtons[i-1]).left(IngredientsPickerView.sidePadding).sizeToFit()
                        //buttonLabel.pin.below(of: button)
                        completedJuiceButtons.append(button)
                    }
                    
                    
                } catch {
                   print("error with loading image")
                }
                
            }
            
            
            //SOFT DRINK COLUMN
            let drinkLabel = UILabel()
            drinkLabel.text = "Soft Drinks"
            self.addSubview(drinkLabel)
            drinkLabel.pin.right(of: label).top(IngredientsPickerView.sidePadding).marginLeft(20).sizeToFit()
            
            var completedDrinkButtons = [UIButton]()
            
            for i in 0...self.data!.drinks.count-1 {
                let juice = self.data!.drinks[i]
                let button = UIButton()
                let buttonLabel = UILabel()
                buttonLabel.text = juice.label
                
                
                let fm = FileManager.default
                do {
                    let documentDirectory = try fm.url(for: .documentDirectory, in: .userDomainMask, appropriateFor:nil, create:false)
                    let imgURL = documentDirectory.appendingPathComponent("\(juice.id)@3x.png")
                    button.setImage(UIImage(contentsOfFile: imgURL.path), for: .normal)
                    button.addAction(events: .touchUpInside) { (button) in
                        //todo fill
                    }
                    self.addSubview(button)
                    //self.addSubview(buttonLabel)
                    
                    if(i == 0) {
                        //first button
                        button.pin.below(of: drinkLabel).right(of: completedJuiceButtons[0]).marginLeft(20).sizeToFit()
                        //buttonLabel.pin.below(of: button).sizeToFit()
                        //completedLabels.append(buttonLabel)
                        completedDrinkButtons.append(button)
                    } else {
                        //not the first button, they should be pinned to the label from above
                        button.pin.below(of: completedDrinkButtons[i-1]).right(of: completedJuiceButtons[i]).marginLeft(20).sizeToFit()
                        //buttonLabel.pin.below(of: button)
                        completedDrinkButtons.append(button)
                    }
                    
                    
                } catch {
                    print("error with loading image")
                }
                
            }
            
            //ALCOHOL COLUMN
            let alcLabel = UILabel()
            alcLabel.text = "Alcohols"
            self.addSubview(alcLabel)
            alcLabel.pin.right(of: drinkLabel).top(IngredientsPickerView.sidePadding).marginLeft(20).sizeToFit()
            
            var completedAlcButtons = [UIButton]()
            
            for i in 0...self.data!.alcohols.count-1 {
                let juice = self.data!.alcohols[i]
                let button = UIButton()
                let buttonLabel = UILabel()
                buttonLabel.text = juice.label
                
                
                let fm = FileManager.default
                do {
                    let documentDirectory = try fm.url(for: .documentDirectory, in: .userDomainMask, appropriateFor:nil, create:false)
                    let imgURL = documentDirectory.appendingPathComponent("\(juice.id)@3x.png")
                    button.setImage(UIImage(contentsOfFile: imgURL.path), for: .normal)
                    button.addAction(events: .touchUpInside) { (button) in
                        //todo fill
                    }
                    self.addSubview(button)
                    //self.addSubview(buttonLabel)
                    
                    if(i == 0) {
                        //first button
                        button.pin.below(of: drinkLabel).right(of: completedDrinkButtons[0]).marginLeft(20).sizeToFit()
                        //buttonLabel.pin.below(of: button).sizeToFit()
                        //completedLabels.append(buttonLabel)
                        completedAlcButtons.append(button)
                    } else {
                        //not the first button, they should be pinned to the label from above
                        button.pin.below(of: completedAlcButtons[i-1]).right(of: completedDrinkButtons[i]).marginLeft(20).sizeToFit()
                        //buttonLabel.pin.below(of: button)
                        completedAlcButtons.append(button)
                    }
                    
                    
                } catch {
                    print("error with loading image")
                }
                
            }
            
            //INGREDIENTS COLUMN
            let ingLabel = UILabel()
            ingLabel.text = "Ingredients"
            self.addSubview(ingLabel)
            ingLabel.pin.right(of: alcLabel).top(IngredientsPickerView.sidePadding).marginLeft(20).sizeToFit()
            
            var completedIngButtons = [UIButton]()
            
            for i in 0...self.data!.ingredients.count-1 {
                let juice = self.data!.ingredients[i]
                let button = UIButton()
                let buttonLabel = UILabel()
                buttonLabel.text = juice.label
                
                
                let fm = FileManager.default
                do {
                    let documentDirectory = try fm.url(for: .documentDirectory, in: .userDomainMask, appropriateFor:nil, create:false)
                    let imgURL = documentDirectory.appendingPathComponent("\(juice.id)@3x.png")
                    button.setImage(UIImage(contentsOfFile: imgURL.path), for: .normal)
                    button.addAction(events: .touchUpInside) { (button) in
                        //todo fill
                    }
                    self.addSubview(button)
                    //self.addSubview(buttonLabel)
                    
                    if(i == 0) {
                        //first button
                        button.pin.below(of: drinkLabel).right(of: completedAlcButtons[0]).marginLeft(20).sizeToFit()
                        //buttonLabel.pin.below(of: button).sizeToFit()
                        //completedLabels.append(buttonLabel)
                        completedIngButtons.append(button)
                    } else {
                        //not the first button, they should be pinned to the label from above
                        button.pin.below(of: completedIngButtons[i-1]).right(of: completedAlcButtons[i]).marginLeft(20).sizeToFit()
                        //buttonLabel.pin.below(of: button)
                        completedIngButtons.append(button)
                    }
                    
                    
                } catch {
                    print("error with loading image")
                }
                
            }
            
            
        }
    }
}
