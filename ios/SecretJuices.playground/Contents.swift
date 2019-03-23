import CommonCrypto
import Foundation
import UIKit
import PlaygroundSupport

PlaygroundPage.current.needsIndefiniteExecution = true



struct Juice{
    var id: Any
    var label: Any
    var type: Any
    var color: Any
    var opacity: Any
    var imageUrl: Any
    
    init?(json: [String:Any]){
        guard
            let id = json["id"],
            let label = json["label"],
            let type = json["type"],
            let color = json["color"],
            let opacity = json["opacity"],
            let imageUrl = URL(string: String(describing: json["imageUrl"]!)) else { return nil }
        
        self.id = id
        self.label = label
        self.type = type
        self.color = color
        self.opacity = opacity
        self.imageUrl = imageUrl
    }
}

func randomString(length: Int) -> String {
    let letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
    return String((0..<length).map{ _ in letters.randomElement()! })
}

func sha1(string: String) -> String?{
    guard let data = string.data(using: String.Encoding.utf8) else { return nil }
    
    let hash = data.withUnsafeBytes { (bytes: UnsafePointer<Data>) -> [UInt8] in
        var hash: [UInt8] = [UInt8](repeating: 0, count: Int(CC_SHA1_DIGEST_LENGTH))
        CC_SHA1(bytes, CC_LONG(data.count), &hash)
        return hash
    }
    
    return hash.map { String(format: "%02x", $0) }.joined()
}

extension URL {
    
    func withQueries(_ queries: [String: String]) -> URL? {
        
        var components = URLComponents(url: self, resolvingAgainstBaseURL: true)
        components?.queryItems = queries.compactMap { URLQueryItem(name: $0.0, value: $0.1) }
        return components?.url
    }
}

//Request authentication key
var randomStr = randomString(length: 8)
let auth = "csgames19-\((Int)(Date().timeIntervalSince1970)/60)-" + randomStr
let hash = sha1(string: auth)
var authKey: String = ""
if let hash = hash{
    authKey = hash
}

var request = URLRequest(url: URL(string: "https://mirego-csgames19.herokuapp.com/ingredients?key=\(randomStr)")!)
request.httpMethod = "GET"

let headers = [
    "Team": "Rochester2",
    "Authorization": authKey
]

request.allHTTPHeaderFields = headers

URLSession.shared.dataTask(with: request) { (data, response, error) in
    
    if let data = data, let string = String(data: data, encoding: .utf8){
    
        print(string)
        
        var outputs = [Juice]()
        do {
            let json = try JSONSerialization.jsonObject(with: data) as? [String: Any]
            if let jsonObject = json {
                let juices = jsonObject["juices"] as? [[String:Any]]
                if let juices = juices{
                    for juice in juices {
                        outputs.append(Juice(json: juice)!)
                    }
                    print(outputs)
                }
            }
        
        }catch let error{
            print(error)
            return
        }
        
    }else{
        print("failed")
    }
}.resume()



