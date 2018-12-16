/**
 *
 */
package com.mevenk.webapp.entity.audit;

import static com.mevenk.webapp.entity.audit.ApplicationException.GENERATOR_APPLICATION_EXCEPTION_ID;
import static javax.persistence.GenerationType.TABLE;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.mevenk.webapp.entity.BaseEntity;
import com.mevenk.webapp.entity.EntitiesMetadata;

/**
 * @author venky
 *
 */
@Entity
@Table(name = "APPLICATION_EXCEPTION")
@TableGenerator(name = GENERATOR_APPLICATION_EXCEPTION_ID, allocationSize = 1, table = EntitiesMetadata.TABLE_NAME_SEQUENCE, pkColumnName = EntitiesMetadata.COLUMN_NAME_SEQUENCE_NAME, pkColumnValue = EntitiesMetadata.SEQUENCE_NAME_APPLICATION_EXCEPTION_ID, valueColumnName = EntitiesMetadata.COLUMN_NAME_SEQUENCE_VALUE)
public class ApplicationException extends BaseEntity {

	protected static final String GENERATOR_APPLICATION_EXCEPTION_ID = "GENERATOR_APPLICATION_EXCEPTION_ID";
	/**
	 *
	 */
	private static final long serialVersionUID = -6632443141738793576L;

	@Id
	@GeneratedValue(generator = GENERATOR_APPLICATION_EXCEPTION_ID, strategy = TABLE)
	@Column(name = "EXCEPTION_ID")
	private Integer exceptionId;
	@Column(name = "EXCEPTION_CLASS")
	private Class<? extends Throwable> exceptionClass;
	@Column(name = "STACK_TRACE")
	private String stackTrace;
	@Column(name = "CREATED_DATE")
	private Date createdDate;
	@Column(name = "UID")
	private String uid;
	@Column(name = "APPLICATION_CORRELAION_ID")
	private String applicationCorrelationId;
	@Column(name = "HTTP_SESSION_ID")
	private String httpSessionId;

	/**
	 * @return the exceptionId
	 */
	public final Integer getExceptionId() {
		return exceptionId;
	}

	/**
	 * @param exceptionId the exceptionId to set
	 */
	public final void setExceptionId(Integer exceptionId) {
		this.exceptionId = exceptionId;
	}

	/**
	 * @return the exceptionClass
	 */
	public final Class<? extends Throwable> getExceptionClass() {
		return exceptionClass;
	}

	/**
	 * @param exceptionClass the exceptionClass to set
	 */
	public final void setExceptionClass(Class<? extends Throwable> exceptionClass) {
		this.exceptionClass = exceptionClass;
	}

	/**
	 * @return the stackTrace
	 */
	public final String getStackTrace() {
		return stackTrace;
	}

	/**
	 * @param stackTrace the stackTrace to set
	 */
	public final void setStackTrace(String stackTrace) {
		this.stackTrace = stackTrace;
	}

	/**
	 * @return the createdDate
	 */
	public final Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public final void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the uid
	 */
	public final String getUid() {
		return uid;
	}

	/**
	 * @param uid the uid to set
	 */
	public final void setUid(String uid) {
		this.uid = uid;
	}

	/**
	 * @return the applicationCorrelationId
	 */
	public final String getApplicationCorrelationId() {
		return applicationCorrelationId;
	}

	/**
	 * @param applicationCorrelationId the applicationCorrelationId to set
	 */
	public final void setApplicationCorrelationId(String applicationCorrelationId) {
		this.applicationCorrelationId = applicationCorrelationId;
	}

	/**
	 * @return the httpSessionId
	 */
	public final String getHttpSessionId() {
		return httpSessionId;
	}

	/**
	 * @param httpSessionId the httpSessionId to set
	 */
	public final void setHttpSessionId(String httpSessionId) {
		this.httpSessionId = httpSessionId;
	}

}
