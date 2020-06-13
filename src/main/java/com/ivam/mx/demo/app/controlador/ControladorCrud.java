package com.ivam.mx.demo.app.controlador;

import com.ivam.mx.demo.app.modelo.Usuario;
import com.ivam.mx.demo.app.modelo.UsuarioCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequestMapping("/crud")
public class ControladorCrud {

    @Autowired
    private UsuarioCrud uc;

    @RequestMapping(value="", method = RequestMethod.GET)
    public String listarUsuarios(ModelMap mp){
        mp.put("usuarios",uc.findAll());
        return "crud/lista";
    }
    @RequestMapping(value="/nuevo",method=RequestMethod.GET)
    public String nuevo(ModelMap mp){
        mp.put("usuario",new Usuario());
        return "crud/nuevo";
    }
    @RequestMapping(value="/crear", method=RequestMethod.POST)
    public String crear(@Valid Usuario usuario,
                        BindingResult bindingResult, ModelMap mp){
        if(bindingResult.hasErrors()){
            return "/crud/nuevo";
        }else{
            uc.save(usuario);
            mp.put("usuario", usuario);
            return "crud/creado";
        }
    }

    @RequestMapping(value="/creado", method = RequestMethod.POST)
    public String creado(@RequestParam("usuario") Usuario usuario){
        return "/crud/creado";
    }
}
