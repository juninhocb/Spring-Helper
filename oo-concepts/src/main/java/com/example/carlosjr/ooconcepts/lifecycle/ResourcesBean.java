package com.example.carlosjr.ooconcepts.lifecycle;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bean")
public class ResourcesBean {
    private final RequestBean requestBean;
    private final SingletonBean singletonBean;
    private final PrototypeBean prototypeBean;

    public ResourcesBean(RequestBean requestBean, SingletonBean singletonBean, PrototypeBean prototypeBean) {
        this.requestBean = requestBean;
        this.singletonBean = singletonBean;
        this.prototypeBean = prototypeBean;
    }

    @GetMapping("/singleton")
    public ResponseEntity<Integer> getSingletonBean(){
        return new ResponseEntity<>(singletonBean.getCount(), HttpStatus.OK);
    }

    @GetMapping("/prototype")
    public ResponseEntity<Integer> getPrototypeBean(){
        return new ResponseEntity<>(prototypeBean.getCount(), HttpStatus.OK);
    }

    @GetMapping("/request")
    public ResponseEntity<Integer> getRequestBean(){
        return new ResponseEntity<>(requestBean.getCount(), HttpStatus.OK);
    }


}
