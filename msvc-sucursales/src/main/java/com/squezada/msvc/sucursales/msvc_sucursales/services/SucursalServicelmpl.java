package com.squezada.msvc.sucursales.msvc_sucursales.services;

import com.squezada.msvc.sucursales.msvc_sucursales.dtos.SucursalDTO;
import com.squezada.msvc.sucursales.msvc_sucursales.models.Sucursal;
import com.squezada.msvc.sucursales.msvc_sucursales.repositories.SucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SucursalServicelmpl implements SucursalService {

    @Autowired
    private SucursalRepository repository;

    private SucursalDTO toDTO(Sucursal sucursal) {
        SucursalDTO dto = new SucursalDTO();
        dto.setIdsucursal(sucursal.getIdsucursal());
        dto.setHorarioAtencion(sucursal.getHorario());
        dto.setDireccion(sucursal.getDireccion());
        return dto;
    }


    private Sucursal toEntity(SucursalDTO dto) {
        Sucursal sucursal = new Sucursal();
        sucursal.setId(dto.getIdsucursal());
        sucursal.setHorario(dto.getHorarioAtencion());
        sucursal.setDireccion(dto.getDireccion());
        return sucursal;

    }

    @Override
    public SucursalDTO save(SucursalDTO dto) {
        Sucursal sucursal = toEntity(dto);
        Sucursal saved = repository.save(sucursal);
        return toDTO(saved);

    }

    @Override
    public List<SucursalDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());

    }

    @Override
    public SucursalDTO findById(Long id) {
        return repository.findById(id)
                .map(this::toDTO)
                .orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }


}


