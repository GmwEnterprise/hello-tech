// electron中不能使用import语法
const { app, BrowserWindow } = require("electron");

// 创建主窗口函数
function createWindow() {
  const win = new BrowserWindow({
    width: 800,
    height: 600,
    webPreferences: {
      // 集成nodejs能力
      nodeIntegration: true,
    },
  });

  // 加载html文件
  win.loadFile("./src/pages/index.html");

  // 打开F12开发者工具
  // win.webContents.openDevTools();
}

// Electron初始化完成时调用createWindow
// 部分 API 在 ready 事件触发后才能使用
app.whenReady().then(createWindow);

app.on("window-all-closed", () => {
  if (process.platform !== "darwin") {
    app.quit();
  }
});

app.on("activate", () => {
  if (BrowserWindow.getAllWindows().length === 0) {
    // 重新创建窗口
    createWindow();
  }
});
