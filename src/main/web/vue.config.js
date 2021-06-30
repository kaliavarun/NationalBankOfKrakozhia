module.exports = {
    // options...
    devServer: {
        // Backend server for CORS
        proxy: {
            '^/api': {
              target: 'http://localhost:8081',
              ws: true,
              changeOrigin: true,
              pathRewrite: {
                '^/api':""
              }
            }
          }
    }
}