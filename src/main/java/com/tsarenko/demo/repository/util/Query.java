package com.tsarenko.demo.repository.util;

public interface Query {
    String INSERT_USER = "INSERT INTO public.user (last_name, first_name, middle_name, date_of_birth) VALUES (:last_name, :first_name, :middle_name, :date_of_birth) RETURNING id";
    String SELECT_USER = "SELECT * FROM public.user WHERE id = :id";;
    String UPDATE_USER = "UPDATE public.user SET last_name = :last_name, first_name = :first_name, middle_name = :middle_name, date_of_birth = :date_of_birth WHERE id = :id RETURNING id";;
    String DELETE_USER = "DELETE FROM public.user WHERE id = :id";
    String SELECT_AVATAR = "SELECT avatar FROM public.user WHERE id = :id";
    String UPDATE_AVATAR = "UPDATE public.user SET avatar = :avatar WHERE id = :id";
    String DELETE_AVATAR = "UPDATE public.user SET avatar = NULL WHERE id = :id";
}
