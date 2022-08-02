- [Verification](Verification.java)是一个注解
- [VerificationInterceptor](VerificationInterceptor.java)是对于该注解的处理
- [Something](Something.java)是该注解的使用
- [SomethingTest](SomethingTest.java)是对注解使用效果的测试

这个aop必须跟spring结合，[TestingWebApplication](TestingWebApplication.java)虽然并没有用到，但是在[SomethingTest](SomethingTest.java)
中使用的是spring test，必须得有这个spring启动项才能进行spring测试。