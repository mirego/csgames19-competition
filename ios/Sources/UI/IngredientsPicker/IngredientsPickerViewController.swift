import UIKit

class IngredientsPickerViewController: BaseViewController {
    private var mainView: IngredientsPickerView {
        return self.view as! IngredientsPickerView
    }

    init() {
        super.init(nibName: nil, bundle: nil)

        modalPresentationStyle = .custom
        transitioningDelegate = self
    }

    required init(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    override func loadView() {
        view = IngredientsPickerView()
        mainView.delegate = self
    }

    override func viewDidLoad() {
        super.viewDidLoad()

        mainView.isLoading = true
        //todo load ingredients from api
        
        //secret ingredients
        let key = "abcdef"
        let time = Date().timeIntervalSince1970
        let epochMin = floor(time / 60)
        let minInt: Int = Int(epochMin)
        let finalStr = "csgames19-\(minInt)-\(key)"
        print("finalStr: \(finalStr)")
        
        let url: String = "https://mirego-csgames19.herokuapp.com/ingredients?key=\(key)"
        var request = URLRequest(url: URL(string: url)!)
        request.httpMethod = "GET"
        request.setValue("Overflowed", forHTTPHeaderField: "Team")
        request.setValue(finalStr.sha1!, forHTTPHeaderField: "Authorization")
        
        
        
        //basic ingredients
        URLSession.shared.dataTask(with: request) { (data: Data?, resp: URLResponse?, err: Error?) in
            if(data != nil) {
                let str = String(data: data!, encoding: .utf8)
                print("data: \(str!)")
                print("Length: \(data!.count)");
                
                do {
                    let decoded: APIData = try JSONDecoder().decode(APIData.self, from: data!)
                    print(decoded.juices[0].color)
                    self.fetchIngredientImages(data: decoded)
                    
                } catch {
                    print("Error with JSON parsing! \(error)")
                }
                
                
                
            } else {
                print("no data!")
            }
        }.resume()
        
        
        
        
        
        
        
        
    }
    
    var completedImageCount = 0;
    func imageComplete(data: APIData) {
        completedImageCount += 1
        if(completedImageCount == data.juices.count + data.drinks.count + data.alcohols.count + data.ingredients.count) {
            //we are done fetching all them images
            DispatchQueue.main.async {
                self.mainView.isLoading = false
                self.mainView.data = data
            }
        }
    }
    
    func fetchIngredientImages(data: APIData) {
        for j in data.juices {
            let url  = j.imageUrl
            let req = URLRequest(url: URL(string: url)!)
            URLSession.shared.dataTask(with: req) { (imgData: Data?, resp: URLResponse?, err: Error?) in
                if(imgData != nil) {
                    let fm = FileManager.default
                    do {
                        let documentDirectory = try fm.url(for: .documentDirectory, in: .userDomainMask, appropriateFor:nil, create:false)
                        let fileURL = documentDirectory.appendingPathComponent("\(j.id)@3x.png")
                        try imgData!.write(to: fileURL)
                        self.imageComplete(data: data)
                    } catch {
                        print("Error w file manager!")
                    }
                } else {
                    print("no data for image!!")
                }
                }.resume()
            
        }
        
        for d in data.drinks {
            let url  = d.imageUrl
            let req = URLRequest(url: URL(string: url)!)
            URLSession.shared.dataTask(with: req) { (imgData: Data?, resp: URLResponse?, err: Error?) in
                if(imgData != nil) {
                    let fm = FileManager.default
                    do {
                        let documentDirectory = try fm.url(for: .documentDirectory, in: .userDomainMask, appropriateFor:nil, create:false)
                        let fileURL = documentDirectory.appendingPathComponent("\(d.id)@3x.png")
                        try imgData!.write(to: fileURL)
                        self.imageComplete(data: data)
                    } catch {
                        print("Error w file manager!")
                    }
                } else {
                    print("no data for image!!")
                }
                }.resume()
            
        }
        
        for a in data.alcohols {
            let url  = a.imageUrl
            let req = URLRequest(url: URL(string: url)!)
            URLSession.shared.dataTask(with: req) { (imgData: Data?, resp: URLResponse?, err: Error?) in
                if(imgData != nil) {
                    let fm = FileManager.default
                    do {
                        let documentDirectory = try fm.url(for: .documentDirectory, in: .userDomainMask, appropriateFor:nil, create:false)
                        let fileURL = documentDirectory.appendingPathComponent("\(a.id)@3x.png")
                        try imgData!.write(to: fileURL)
                        self.imageComplete(data: data)
                    } catch {
                        print("Error w file manager!")
                    }
                } else {
                    print("no data for image!!")
                }
                }.resume()
            
        }
        
        for i in data.ingredients {
            let url  = i.imageUrl
            let req = URLRequest(url: URL(string: url)!)
            URLSession.shared.dataTask(with: req) { (imgData: Data?, resp: URLResponse?, err: Error?) in
                if(imgData != nil) {
                    let fm = FileManager.default
                    do {
                        let documentDirectory = try fm.url(for: .documentDirectory, in: .userDomainMask, appropriateFor:nil, create:false)
                        let fileURL = documentDirectory.appendingPathComponent("\(i.id)@3x.png")
                        try imgData!.write(to: fileURL)
                        self.imageComplete(data: data)
                    } catch {
                        print("Error w file manager!")
                    }
                } else {
                    print("no data for image!!")
                }
                }.resume()
            
        }
    }
}

extension IngredientsPickerViewController: UIViewControllerTransitioningDelegate {
    func presentationController(forPresented presented: UIViewController, presenting: UIViewController?, source: UIViewController) -> UIPresentationController? {
        return CustomPresentationController(presentedViewController: presented, presentingViewController: presenting, layoutType: .bottom)
    }
}

extension IngredientsPickerViewController: IngredientsPickerViewDelegate {
    func didTapCloseButton() {
        dismiss(animated: true, completion: nil)
    }
}
