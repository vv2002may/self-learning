import express from "express";
import client from "prom-client";
import { requestCountMiddleware } from "./monitoring/requestCountMiddleware";
import { requestGaugeMiddleware } from "./monitoring/requestGaugeMiddleware";
import { requestHistogramMiddleware } from "./monitoring/requestHistogramMiddleware";


const app = express();

// app.use(requestCountMiddleware);

// app.use(requestGaugeMiddleware);

app.use(requestHistogramMiddleware);

app.get("/user", (req, res) => {
    res.json({
        name: "Vimal"
    })
});

app.post("/user", (req, res) => {
    res.json({
        name: "Vimal"
    })
});

app.get("/metrics", async (req, res) => {
    res.set("Content-Type", client.register.contentType);
    res.send(await client.register.metrics());
});

app.listen(3000, () => {
    console.log("Listening at 3000");
});