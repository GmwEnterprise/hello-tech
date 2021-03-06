module.exports = {
  pages: {
    index: {
      entry: "examples/main.ts",
      template: "public/index.html",
      filename: "index.html",
    },
  },
  chainWebpack: (config) => {
    config.module
      .rule("ts")
      .include.add("/packages")
      .end()
      .use("babel")
      .loader("babel-loader");
  },
};
