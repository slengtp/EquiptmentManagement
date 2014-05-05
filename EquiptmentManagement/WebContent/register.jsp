<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>员工注册</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script src="script/jquery-2.0.2.js" type="text/javascript"></script>
    <script src="script/bootstrap.js" type="text/javascript"></script>
    <link rel="stylesheet" href="css/bootstrap-theme.css" type="text/css">
    <link rel="stylesheet" href="css/bootstrap.css" type="text/css">
    <link rel="stylesheet" href="css/bootstrap-combined.min.css" type="text/css">
    <link rel="stylesheet" href="css/layoutit.css" type="text/css">
    <link rel="stylesheet" href="css/register.css" type="text/css">
</head>
<body>
<div class="container-fluid signup">
    <div class="row-fluid">
        <div class="span12 top-head ">
            <h3>
                <span class="glyphicon glyphicon-cog top-head-logo"></span>
                企业设备管理系统
            </h3>
        </div>
    </div>

    <div class="row-fluid container">
        <div class="main-box">
            <div class="show">
                <div class="int">
                    <label for="username">用户ID  </label>
                    <input type="text" id="userID" class="required" placeholder="必填"/>
                    <span class="high">*</span>
                </div>
                <div class="int">
                    <label for="username">姓名  </label>
                    <input type="text" id="username" class="required" placeholder="必填"/>
                    <span class="high">*</span>
                </div>
                <div class="int">
                    <label for="username">密码  </label>
                    <input type="text" id="password" class="required" placeholder="必填"/>
                    <span class="high">*</span>
                </div>
                <div class="int">
                    <label for="password2">确认密码  </label>
                    <input type="text" id="password2" class="required" placeholder="必填"/>
                    <span class="high">*</span>
                </div>
                <div class="int">
                    <label for="apartment">部门  </label>
                    <input type="text" id="apartment" class="required" placeholder="必填"/>
                    <span class="high">*</span>
                </div>
                <div class="int">
                    <label for="position">职位  </label>
                    <input type="text" id="position" />

                </div>
                <div class="int">
                    <label for="phone">联系电话  </label>
                    <input type="text" id="phone" class="required" placeholder="必填"/>
                    <span class="high">*</span>
                </div>
                <div class="int">
                    <label for="email">邮箱  </label>
                    <input type="text" id="email"/>

                </div>
                <!--                 <div class="int">
                                     <label for="personalInfo">个人资料：</label>
                                     <input type="text" id="personalInfo" />
                                 </div>-->
                <div class="submit">

                    <a class="btn btn-primary btn-large" href="#">提交</a>


<!--                    <input type="submit" value="提交" id="send"/>
                    <input type="reset" id="res">-->
                </div>
            </div>

        </div>
    </div>

    <div class="row-fluid bottom">
        <div class="span12">
            <div>
                <p>
					<strong>Copy Right</strong>&nbsp;Author: 庄玮
                </p>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    var userRight = 0;
    var emailRight = 0;

    $(function(){
        $('#username').blur(function(){  // 添加失去焦点事件
            var $parent = $(this).parent();
            $parent.find(".tip").remove();
            if(this.value==""||this.value.length < 6){  //如果用户输入长度小于6
                var $tip = $("<span class='tip'><img src='images/reg3.gif' align='top' />请输入长度大于6的用户名</span>");
                $parent.append($tip);

                userRight = 0;
            }else{
                var $tip = $("<span class='tip'><img src='images/reg4.gif'align='top' />输入正确</span>")
                $parent.append($tip);
                userRight = 1;
            }
        })

        $('#password2').blur(function(){  // 添加失去焦点事件
            var $parent = $(this).parent();
            $parent.find(".tip").remove();
            var $pass1 =  $("#password1").value;
            if(this.value== $('#password1').value){  //如果用户输入两次密码相同
                var $tip = $("<span class='tip'><img src='images/reg4.gif' align='top' />两次输入相同</span>");
                $parent.append($tip);
                userRight = 0;
                alert(this.value);
                alert($pass1.value);

            }else{

                alert(this.value);          alert($pass1.value);

                var $tip = $("<span class='tip'><img src='images/reg3.gif'align='top' />两次输入密码不同，请重新输入</span>")
                $parent.append($tip);
                userRight = 1;

            }
        })

        $('#email').blur(function(){  // 添加失去焦点事件
            var $parent = $(this).parent();
            $parent.find(".tip").remove();
            if( this.value=="" || ( this.value!="" && !/.+@.+\.[a-zA-Z]{2,4}$/.test(this.value) ) ){
                var $tip = $("<span class='tip'><img src='images/reg3.gif' align='top' />请输入正确的邮箱地址</span>");
                $parent.append($tip);

                emailRight = 0;
            }else{
                var $tip = $("<span class='tip'><img src='images/reg4.gif'align='top' />输入正确</span>")
                $parent.append($tip);
                emailRight = 1;

            }
        })

        $("#send").click(function(){
            if(emailRight && userRight)
            {
                alert("注册成功");
            }else
                alert("请完整填入信息");
            //return false;
        })
    })
</script>
</body>
</html>