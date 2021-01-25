import express from 'express';

const app = express();

app.get("/", (req, res, next) => {
    return res.send('Hello, World!');
});

app.listen(3333);
