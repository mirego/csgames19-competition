import Alamofire
import CryptoSwift

protocol DataSourceDelegate: class {
    func didRefreshData()
}

class DataSource: NSObject {
    //fileprivate let API = "https://mirego-csgames19.herokuapp.com/"
    

    
    fileprivate let API_INGREDIENTS = "https://mirego-csgames19.herokuapp.com/ingredients"
    
    fileprivate let headers : HTTPHeaders = [
        "Team" : "ETS of Mythology"
    ]
    
    fileprivate let API_SERVE = "https://mirego-csgames19.herokuapp.com/serve"
    //    fileprivate(set) var currentDate: Date?
    weak var delegate: DataSourceDelegate?
    var ingView: IngredientsPickerViewController?
    
    func getIngredients() {
        Alamofire.request(API_INGREDIENTS, headers: headers).responseJSON{ response in
            switch response.result {
            case .success(let data):
                do {
                    print(data)
                    //                    let time = try JSONDecoder().decode(Time.self, from: data)
                    //                    self.currentDate = time.date
                } catch {
                    //                    self.currentDate = nil
                }
                
            case .failure:
                print("failure")
                //                self.currentDate = nil
            }
            
            self.delegate?.didRefreshData()
        }
    }
    
    func getSecretIngredients() {
        
        let key = UUID().uuidString
        let epoch = Int(Date().timeIntervalSince1970 / 60)
        let auth = "csgames19-\(epoch)-\(key)"
        
        let API_INGREDIENTS = "https://mirego-csgames19.herokuapp.com/ingredients?key=\(key)"
        
        let headers : HTTPHeaders = [
            "Team" : "ETS of Mythology",
            "Authorization" : auth.sha1()
        ]
        
        print(auth.sha1())
        
        Alamofire.request(API_INGREDIENTS, headers: headers).responseJSON{ response in
            switch response.result {
            case .success(let data):
                do {
                    print(data)
                    print(type(of: data))
                    self.ingView?.updateView(data: data as! NSDictionary)
                    //                    let time = try JSONDecoder().decode(Time.self, from: data)
                    //                    self.currentDate = time.date
                } catch {
                    //                    self.currentDate = nil
                }
                
            case .failure:
                print("failure")
                //                self.currentDate = nil
            }
            
            self.delegate?.didRefreshData()
        }
    }
    func setView(view: IngredientsPickerViewController) {
        self.ingView = view
    }
    func serve() {
        
    }
}
