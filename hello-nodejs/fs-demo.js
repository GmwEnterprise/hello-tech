const fs = require("fs");
const { sep } = require("path");

/**
 * 在指定路径中写入文件内容，路径不存在则递归创建；文件有内容则覆盖。
 * @param {string} content 字符串内容
 * @param {string} filepath 文件路径
 */
function writeFile(content, filepath) {
  const lastSep = filepath.lastIndexOf(sep);
  fs.mkdirSync(filepath.substring(0, lastSep), { recursive: true });
  fs.writeFileSync(filepath, content);
}

/**
 * 判断路径是否存在
 * @param {string} path 文件路径 | 文件夹路径
 * @returns {number} 1-文件，2-文件夹，0-不存在
 */
function checkPathExist(path) {
  try {
    const stat = fs.statSync(path);
    return stat.isFile() ? 1 : stat.isDirectory() ? 2 : 0;
  } catch (err) {
    return 0;
  }
}

/**
 * 读取文件内容，返回内容字符串
 * @param {string} path 文件路径
 * @returns {string} 字符串内容，如果路径不存在则返回 null
 */
function readFile(path) {
  if (checkPathExist(path) === 1) {
    return fs.readFileSync(path).toString();
  }
  return null;
}
