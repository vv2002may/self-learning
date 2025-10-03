import client from "prom-client";

const requestHistogram = new client.Histogram({
    name: "http_request_duration_ms",
    help: "Duration of HTTP requests in ms",
    labelNames: ["method", "route", "status_code"],
    bucket: [0.1, 5, 15, 50, 100, 300, 500, 1000, 3000, 5000]
});

export const requestHistogramMiddleware = (req, res, next) => {
    const startTime = Date.now();
    res.on("finish", () => {
        const endTime = Date.now();
        requestHistogram.observe({
            method: req.method,
            route: req.path,
            // status_code: res.statusCode
        }, endTime - startTime);
    });
    next();
}