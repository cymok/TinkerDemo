##废话不多说 先看资料吧

>>[tinker github](https://github.com/Tencent/tinker)

>>[使用博客](https://www.jianshu.com/p/72ba9043a82c)

>>[第三方Tinker后台平台](http://tinkerpatch.com/Price)

##遇到的问题

- 1
[
TINKER_ID
](https://www.google.com)
>>默认使用 `gradle.properties` 里面的常量, 没有则获取git的head节点hash短值

- 2
[
`com.tencent.tinker.build.util.TinkerPatchException:Warning: ignoreWarning is false, manifest was changed, while hot plug component support mode is disabled. Such changes will not take effect.`
](https://www.jianshu.com/p/6a23103b64fb)

>>解决方案:

>>修改 `ignoreWarning = false` 为 `ignoreWarning = true`

