/*
 * Copyright 2012-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package open.springboot.mail.service;

import open.springboot.mail.model.Email;
import open.springboot.mail.service.Exception.CannotSendEmailException;

import javax.mail.internet.MimeMessage;
import javax.validation.constraints.NotNull;
import java.util.Map;

public interface EmailService {

    /**
     * Send an email message.
     *
     * The send date is set or overridden if any is present.
     *
     * @param mimeEmail an email to be send
     */
    MimeMessage send(@NotNull Email mimeEmail);

    /**
     * Send an email message.
     *
     * The body is ignored if present.
     * The send date is set or overridden if any is present.
     *
     * @param mimeEmail an email to be send
     * @param template the reference to the template file
     * @param modelObject the model object to be used for the template engine
     */
    MimeMessage send(@NotNull Email mimeEmail,
                     @NotNull String template, @NotNull Map<String, Object> modelObject) throws CannotSendEmailException;

}