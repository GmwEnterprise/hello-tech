module.exports = {
  devServer: {
    proxy: {
      '/api': {
        target: 'http://localhost:4200/hello-im/api',
        pathRewrite: { '^/api': '' },
      },
    },
  },
}
