package com.popup_the_local.server.service

import com.popup_the_local.server.common.cloudstorage.CloudStorageService
import com.popup_the_local.server.common.responsebody.InvalidInputException
import com.popup_the_local.server.dto.ApplyPopupRequest
import com.popup_the_local.server.dto.ApplyPopupResponse
import com.popup_the_local.server.entity.Application
import com.popup_the_local.server.repository.ApplicationRepository
import com.popup_the_local.server.repository.MemberRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class ApplicationService(
    private val memberRepository: MemberRepository,
    private val applicationRepository: ApplicationRepository,
    private val cloudStorageService: CloudStorageService
) {

    @Transactional
    fun applyPopup(memberId: String, request: ApplyPopupRequest): ApplyPopupResponse {
        val member = memberRepository.findByIdOrNull(memberId) ?: throw InvalidInputException(fieldName = "member")
        val imageUrlList: MutableList<String> = request.images.map { image ->
            cloudStorageService.uploadObject(image)
        }.toMutableList()


        val application = Application.createApplication(
            title = request.title,
            description = request.description,
            startDate = request.startDate,
            endDate = request.endDate,
            category = request.category,
            member = member,
            address = request.address,
            images = imageUrlList.toMutableList()

        ).apply {
            applicationRepository.save(this)
        }

        return ApplyPopupResponse(
            application.id
        )
    }
}