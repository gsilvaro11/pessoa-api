package com.dbc.pessoaapi.service;

import com.dbc.pessoaapi.dto.PessoaDTO;
import com.dbc.pessoaapi.entity.PessoaEntity;
import freemarker.core.DirectiveCallPlace;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender emailSender;
    @Value("${spring.mail.username}")
    private String remetente;
    private final Configuration configuration;

    public void enviarEmailSimples() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(remetente);
        message.setTo("nei.guilherme@dbccompany.com.br");
        message.setSubject("Assunto do e-mail");
        message.setText("Olá,\n\nEste é um exemplo de envio de e-mail pelo JavaMail!\n\n\nAtt,\nMaicon.");
        emailSender.send(message);
    }

    public PessoaDTO enviarEmailSimples(PessoaDTO pessoaDTO) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(remetente);
        message.setTo("nei.guilherme@dbccompany.com.br");
        message.setSubject("Assunto do e-mail");
        message.setText("Olá " + pessoaDTO.getNome() +",\n\n" +
                "Estamos felizes em ter você em nosso sistema!!\n\n" +
                "Seu Cadastro foi realizado com sucesso, seu identificador é " + pessoaDTO.getIdPessoa() +
                "\nQualquer dúvida é só contatar o suporte pelo email " + remetente +
                "\n\n\nAtt,\n Guilherme Silva.");
        emailSender.send(message);

        return pessoaDTO;
    }

    public PessoaDTO enviarEmailComTemplate(PessoaDTO pessoaDTO, String tipo)
            throws MessagingException, IOException, TemplateException {
        MimeMessage mimeMessage = emailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setFrom(remetente);
        helper.setTo(pessoaDTO.getEmail());
        helper.setSubject("Email com template Exemplo");

        Template template = configuration.getTemplate("email-template.ftl");
        Map<String, Object> dados = new HashMap<>();
        dados.put("nome", pessoaDTO.getNome());
        dados.put("id", pessoaDTO.getIdPessoa());
        dados.put("remetente", remetente);
        dados.put("mensagem", tipo);


        dados.put("logo", "logo.png");
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, dados);

        helper.setText(html, true);

        File file = new File("logo.png");
        FileSystemResource fileSystemResource = new FileSystemResource(file);
        helper.addAttachment("logo.png", fileSystemResource);

        emailSender.send(mimeMessage);
        return pessoaDTO;
    }

    public PessoaDTO enviarEmailComTemplateDelete(PessoaDTO pessoaDTO)
            throws MessagingException, IOException, TemplateException {
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setFrom(remetente);
        helper.setTo(pessoaDTO.getEmail());
        helper.setSubject("Email com template Exemplo");
        Template template = configuration.getTemplate("email-templateDelete.ftl");
        Map<String, Object> dados = new HashMap<>();
        dados.put("nome", pessoaDTO.getNome());
        dados.put("remetente", remetente);
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, dados);

        helper.setText(html, true);


        emailSender.send(mimeMessage);
        return pessoaDTO;
    }

}
