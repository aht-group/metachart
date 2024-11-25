const path = require('path');
module.exports = {
  mode: process.env.NODE_ENV === 'development' ? 'development' : 'production',
  entry: {
    pivot: './src/pivot.ts',
    hc: './src/hc.ts',
    mc: './src/mc.ts'
  },
  resolve: {
    extensions: ['.tsx', '.ts', '.js'], // resolve these extensions
  },
  output: {
    filename: '[name].bundle.js',
    path: path.resolve(__dirname, './dist/')
    },
  module: {
    rules: [
      {
        test: /\.tsx?$/, // regex to select only .ts or .tsx files
        use: 'ts-loader', // use ts-loader to transpile TypeScript code to JavaScript
        exclude: /node_modules/, // exclude node_modules directory
      },
      {
        test: /\.js$/, // regex to select only .js files
        exclude: /node_modules/, // exclude node_modules directory
        include: path.resolve(__dirname, 'src/js'), // only include .js files from the src/js directory
        use: {
          loader: 'babel-loader',
          options: {
            presets: ['@babel/preset-env'] // use the @babel/preset-env preset
          }
        }
      }
    ]
  },
 
  devtool: 'source-map',
  optimization: {
    minimize: false
  }
};