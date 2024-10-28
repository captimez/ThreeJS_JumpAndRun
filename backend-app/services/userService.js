const User = require("../models/User")

export class UserService{

    async saveUser(userBody) {
        try{
            const user = new User(userBody)
    
            await user.save()
        }catch(error){
            throw new Error("Error occured while saveing user!")
        }
    }


}

export default new UserService();