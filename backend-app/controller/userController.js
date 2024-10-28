import UserService from "../services/userService";

export class UserController{

    async handleUserSave(req,res) {
        try{
            await UserService.saveUser(req.body)
        }catch(error){
            return error.message;
        }
    }
}

export default new UserController();