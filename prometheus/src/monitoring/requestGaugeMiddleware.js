import client from "prom-client";

const requestGauge = new client.Gauge({
    name: "active_users",
    help: "total no. of users whose request is not resolved",
    // labelNames: ["method", "route", "status_code"]
    labelNames: ["method", "route"]

});

export const requestGaugeMiddleware = (req, res, next) => {

    requestGauge.inc({
        method: req.method,
        route: req.path,
        // status_code: res.statusCode
    });

    res.on("finish", () => {
        setTimeout(() => {
            requestGauge.dec({
                method: req.method,
                route: req.path,
                // status_code: res.statusCode
            })
        }, 5000);
    })
    next();
}
