const fs = require("fs");

/**
 * 检查传入的文件夹路径是否存在
 * @param {string} dir 文件夹路径
 */
function dirExist(dir) {
  try {
    const stat = fs.statSync(dir);
    return stat.isDirectory();
  } catch (err) {
    return false;
  }
}

/**
 * 检查传入的文件路径是否存在
 * @param {string} file 文件路径
 */
function fileExist(file) {
  try {
    const stat = fs.statSync(file);
    return stat.isFile();
  } catch (err) {
    return false;
  }
}

/**
 * 若指定文件不存在则创建文件
 * @param {string} filepath 文件路径
 */
function createFile(filepath) {}
