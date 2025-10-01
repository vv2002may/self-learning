import client from "prom-client";

const requestCounter = new client.Counter({
    name: "request_count",
    help: "Total request count",
    labelNames: ["method", "route", "status_code"]
});

export const requestCountMiddleware = (req, res, next) => {

    requestCounter.inc({
        method: req.method,
        route: req.path,
        status_code: res.statusCode
    });
    next();
}
