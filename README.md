# EmulationSystemFrontend

基于 [Element Plus](https://element-plus.org/) 的电路物联网仿真前端，具体请见：[https://modao.cc/app/6XdQIh9Es69j5wU7F934Ol](https://modao.cc/app/6XdQIh9Es69j5wU7F934Ol)

## 项目配置

```bash
pnpm install
pnpm run dev    # 开发
pnpm run build  # 部署
```

生成的部署文件位于 `./dist/`。

## 一些备忘录

- `/image/uploadImage` 的文件需要通过 form-data 进行传递，且 **必须是特定格式**（压缩包内必须包含特定的内容后端才能正确响应）。

## 贡献者

- [@10PAULODYBALA](https://github.com/10PAULODYBALA)
- [@BlockLune](https://github.com/BlockLune)
- [@haozihuilaile](https://github.com/haozihuilaile)
- [@kongdqdsqd](https://github.com/kongdqdsqd)
