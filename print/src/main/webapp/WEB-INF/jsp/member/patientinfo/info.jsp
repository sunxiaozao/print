<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../common/pagestart.jsp"%>
<div class="container-fluid form-box">
	<form id="patientEdit" action="${SITE.context}/member/updateUserInfo"
		method="post" class="form-horizontal" enctype="multipart/form-data">
		<div class="form-box-title">
			<h3>
				完善个人信息 <small>请填写个人信息，然后保存。</small>
			</h3>
		</div>
		<div class="control-group">
			<label class="control-label"> 医保卡号: </label>
			<div class="controls">
				<input type="hidden" id="id" name="id" value="${patient.id}">
				<input type="text" id="cardId" name="cardId"
					value="${patient.cardId}" class="input w370 ">

			</div>
		</div>
		<div class="control-group">
			<label class="control-label"> 所属医院: </label>
			<div class="controls">
				<select name="hospitalId">


					<c:forEach items="${hospitalMap}" var="hospital">
						<option value="${hospital.key}"
							<c:if test="${hospital.key eq patient.hospitalId}">selected</c:if>>${hospital.value}</option>
					</c:forEach>
				</select>

			</div>
		</div>

		<div class="control-group">
			<label class="control-label"> IC卡: </label>
			<div class="controls">
				<div class="row-fluid">
					<div class="span3">
						<input type="text" id="ic" name="icId" value="${patient.icId}"
							class="input w370 ">
					</div>
					<div class="span3">
						<label class="checkbox"><input type="checkbox" value="I"
							name="icLoginFlag" <c:if test="${ic }">checked </c:if> /> &nbsp;作为登录账户</label>
					</div>
				</div>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"> 手机: </label>
			<div class="controls">
				<div class="row-fluid">
					<div class="span3">
						<input type="text" id="mobile" name="mobile"
							value="${patient.mobile}" class="input w370 validate[custom[phone]]">
					</div>
					<div class="span3">
						<label class="checkbox"><input type="checkbox" value="M"
							name="icLoginFlag" <c:if test="${mobile }">checked </c:if> />
							&nbsp;作为登录账户</label>
					</div>
				</div>


			</div>
		</div>

		<div class="control-group">
			<label class="control-label"> 邮箱: </label>
			<div class="controls">
				<div class="row-fluid">
					<div class="span3">
						<input type="text" id="email" name="email"
							value="${patient.email}" class="input w370 validate[custom[email]]">
					</div>
					<div class="span3">
						<label class="checkbox"><input type="checkbox" value="E"
							name="icLoginFlag" <c:if test="${email }">checked </c:if> />
							&nbsp;作为登录账户</label>
					</div>
				</div>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"> 身份证号: </label>
			<div class="controls">
				<input type="text" id="identityCard" name="identityCard"
					value="${patient.identityCard}" class="input w370 ">

			</div>
		</div>
		<div class="control-group">
			<label class="control-label"> X线片号: </label>
			<div class="controls">
				<input type="text" id="xPictureId" name="xPictureId"
					value="${patient.XPictureId}" class="input w370 ">

			</div>
		</div>
		<div class="control-group">
			<label class="control-label"> 姓名： </label>
			<div class="controls">
				<input type="text" id="fullname" name="fullname"
					value="${patient.fullname}" class="input w370 validate[required]">

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">性别: </label>
			<div class="controls">
				<select name="sex" class="sex" class="validate[required]">
					<c:forEach items="${sexMap}" var="sex">
						<option value="${sex.key}"
							<c:if test="${sex.key eq patient.sex}">selected</c:if>>${sex.value}</option>
					</c:forEach>
				</select>

			</div>
		</div>


		<div class="control-group">
			<label class="control-label"> 出生日期： </label>
			<div class="controls">
				<input type="text" id="birthday" name="birthday"
					value="<fmt:formatDate value="${patient.birthday}" pattern="yyyy-MM-dd" type="date" dateStyle="long" />"
					class="form_date input w370 ">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"> 籍贯: </label>
			<div class="controls">
				<input type="text" id="birthPlace" name="birthPlace"
					value="${patient.birthPlace}" class="input w370 ">

			</div>
		</div>
		<div class="control-group">
			<label class="control-label"> 照片: </label>
			<div class="controls">
				<div class="b-fileupload-row">
					<div data-provides="fileupload" class="fileupload fileupload-new">
						<div class="input-append">
							<div class="uneditable-input span2">
								<i class="icon-file fileupload-exists"></i> <span
									class="fileupload-preview"></span>
							</div>
							<span class="btn btn-file"> <span class="fileupload-new">选择文件</span>
								<span class="fileupload-exists">重新选择</span> <input type="file"
								name="file" id="file" class="default "> </span> <a
								data-dismiss="fileupload" class="btn fileupload-exists" href="#">清除</a>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"> 药物过敏: </label>
			<div class="controls">
				<input type="text" id="medicineHypersusceptibility"
					name="medicineHypersusceptibility"
					value="${patient.medicineHypersusceptibility}" class="input w370 ">

			</div>
		</div>

		<div class="control-group">
			<label class="control-label"> 血型: </label>
			<div class="controls">
				<input type="text" id="bloodType" name="bloodType"
					value="${patient.bloodType}" class="input w370 ">

			</div>
		</div>
		<div class="control-group">
			<label class="control-label"> 婚姻状况: </label>
			<div class="controls">
				<input type="text" id="marryStaus" name="marryStaus"
					value="${patient.marryStaus}" class="input w370 ">

			</div>
		</div>
		<div class="control-group">
			<label class="control-label"> 家族病史: </label>
			<div class="controls">
				<input type="text" id="familyIllHistory" name="familyIllHistory"
					value="${patient.familyIllHistory}" class="input w370 ">

			</div>
		</div>


		<div class="control-group">
			<div class="controls">
				<button type="submit" class="btn btn-primary" id="submit">
					<i class="icon-ok icon-white"></i> 保存
				</button>
				&nbsp;&nbsp; <a class="btn" href="${SITE.context}/member/index">返回</a>

			</div>
		</div>
	</form>
</div>

<a data-trigger="modal" href="test_page_scroll.html" data-cache="false"
	data-title="Ajax Modal Title"></a>



