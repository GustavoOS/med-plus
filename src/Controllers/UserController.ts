import { Request, Response } from 'express';

const user = {
    name: "Gustavo",
    email: "thats@mybrain.com"
}

export default {
    async index(req: Request, res: Response) {
        return res.json(user);
    }
}
