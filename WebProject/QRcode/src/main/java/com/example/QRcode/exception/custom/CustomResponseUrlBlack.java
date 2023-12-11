package com.example.QRcode.exception.custom;


import com.example.QRcode.entity.UrlBlacklist;
import com.example.QRcode.model.UrlDTOBlack;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@AllArgsConstructor

public class CustomResponseUrlBlack  {
    private UrlDTOBlack urlDTOBlack;

    @Override
    public String toString() {
        return "CustomResponseUrlBlack{" +
                "urlDTOBlack=" + urlDTOBlack +
                '}';
    }
}
