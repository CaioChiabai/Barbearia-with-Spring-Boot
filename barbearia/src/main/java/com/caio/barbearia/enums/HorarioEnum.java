package com.caio.barbearia.enums;

import java.util.ArrayList;
import java.util.List;

public enum HorarioEnum {

    H08_00("08:00"),
    H08_30("08:30"),
    H09_00("09:00"),
    H09_30("09:30"),
    H10_00("10:00"),
    H10_30("10:30"),
    H11_00("11:00"),
    H11_30("11:30"),
    H12_00("12:00"),
    H12_30("12:30"),
    H13_00("13:00"),
    H13_30("13:30"),
    H14_00("14:00"),
    H14_30("14:30"),
    H15_00("15:00"),
    H15_30("15:30"),
    H16_00("16:00"),
    H16_30("16:30"),
    H17_00("17:00"),
    H17_30("17:30"),
    H18_00("18:00");

    private final String horario;
    
    HorarioEnum(String horario) {
        this.horario = horario;
    }

    public String getHorario() {
        return horario;
    }

    public static List<String> getTodosHorarios() {
        List<String> horarios = new ArrayList<>();
        for (HorarioEnum horarioEnum : values()) {
            horarios.add(horarioEnum.horario);
        }
        return horarios;
    }

    public static List<String> getHorariosEntre(String inicio, String fim) {
        List<String> horarios = new ArrayList<>();
        boolean dentroDoIntervalo = false;

        for (HorarioEnum horarioEnum : values()) {
            if (horarioEnum.horario.equals(inicio)) {
                dentroDoIntervalo = true;
            }
            if (dentroDoIntervalo) {
                horarios.add(horarioEnum.horario);
            }
            if (horarioEnum.horario.equals(fim)) {
                break;
            }
        }
        return horarios;
    }
}
