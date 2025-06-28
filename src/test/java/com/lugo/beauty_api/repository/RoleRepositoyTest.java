package com.lugo.beauty_api.repository;

import com.lugo.beauty_api.model.Rol;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class RoleRepositoyTest {

    @Autowired
    private RolRepositoy rolRepositoy;

    @Test
    void testGuardarYRecuperarRol() {
        // Crear un rol
        Rol rol = new Rol();
        rol.setName("admin");

        // Guardar
        Rol guardado = rolRepositoy.save(rol);

        // Verificar que se guardó
        assertThat(guardado.getId()).isNotNull();
        assertThat(guardado.getName()).isEqualTo("admin");
    }
}