/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.biniek.view.exceptionhandlers;

import javax.interceptor.InterceptorBinding;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;



@InterceptorBinding
@Target({ TYPE, METHOD })
@Retention(RUNTIME)
public @interface BindingInterfaceWeb {
    
   // @Nonbinding boolean isTestButtons() default true;
}