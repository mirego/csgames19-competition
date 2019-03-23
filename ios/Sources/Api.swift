//
//  Api.swift
//  MixParadise
//
//  Created by Nicho Mercier on 2019-03-23.
//  Copyright © 2019 Mirego. All rights reserved.
//


import Foundation
import Alamofire

var base = "https://mirego-csgames19.herokuapp.com"
let key = "dhdsgfisdhdgfaksdjhgfa"

func getIngredients(_ secret:Bool = false, callback:@escaping (NSDictionary) -> ()) {
    Alamofire
        .request("\(base)/ingredients\(secret ? "?key=\(key)" : "")", headers: generateHeaders(secret ? key : nil))
        .responseJSON(completionHandler: { response in
            if(response.response?.statusCode == 200) {
                if let result = response.result.value {
                    let data = result as! NSDictionary
                    callback(data)
                }
            }
    })
}

/*func serve() {
    let parameters: [String: AnyObject] = [
        :
    ]
    Alamofire.request(.POST, "\(base)/serve", parameters: parameters, encoding: .JSON)
        .responseJSON { request, response, JSON, error in
            print(response)
            print(JSON)
            print(error)
    }
    
    
}*/

/**
 * Optional key argument to get
 */
func generateHeaders(_ key:String? = nil) -> HTTPHeaders {
    let prefix = "csgames19"
    let epoch = Int(NSDate().timeIntervalSince1970) / 60
    let authorization = key != nil ? ("\(prefix)-\(epoch)-\(key!)".sha1()) : ""
    return [
        "Team": "Laurentian",
        "Authorization": authorization
    ]
}



extension String {
    func sha1() -> String {
        let data = self.data(using: String.Encoding.utf8)!
        var digest = [UInt8](repeating: 0, count:Int(CC_SHA1_DIGEST_LENGTH))
        data.withUnsafeBytes {
            _ = CC_SHA1($0, CC_LONG(data.count), &digest)
        }
        let hexBytes = digest.map { String(format: "%02hhx", $0) }
        return hexBytes.joined()
    }
}
