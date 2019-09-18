/**
 * MIT License
 * <p>
 * Copyright (c) 2017-2018 nuls.io
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package io.nuls.dapp.communitygovernance.api;

import io.nuls.core.rpc.model.Parameter;
import io.nuls.core.rpc.model.Parameters;
import io.nuls.core.rpc.model.TypeDescriptor;
import io.nuls.dapp.communitygovernance.api.request.CouncilApplicantSearch;
import io.nuls.dapp.communitygovernance.api.request.MandatorsSearch;
import io.nuls.dapp.communitygovernance.api.request.ProposaListSearch;
import io.nuls.dapp.communitygovernance.api.request.VoteListSearch;
import io.nuls.dapp.communitygovernance.api.response.ResponseData;
import io.nuls.dapp.communitygovernance.model.dto.*;
import io.nuls.v2.model.annotation.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: PierreLuo
 * @date: 2019-08-18
 */
@RestController
@RequestMapping("/cg")
public class CommunityGovernanceApi {
    final Logger logger = LoggerFactory.getLogger(getClass());


    @PostMapping("/mandator/list")
    @ApiOperation(description = "查询代理人的委托地址列表",order = 101)
    @Parameters({
            @Parameter(parameterName = "查询代理人的委托地址列表", parameterDes = "查询代理人的委托地址列表表单", requestType = @TypeDescriptor(value = MandatorsSearch.class))
    })
    @io.nuls.core.rpc.model.ResponseData(name = "返回值", description = "代理人的委托地址列表", responseType = @TypeDescriptor(value = List.class, collectionElement = MandatorDto.class))
    public ResponseData mandatorList(@RequestBody MandatorsSearch mandatorsSearch) {
        try {
            List<MandatorDto> mandatorList = new ArrayList<>();
            ResponseData result = ResponseData.success();
            result.setData(mandatorList);
            return result;
        } catch (Exception e) {
            logger.error("system error", e);
            return ResponseData.error(e.getMessage());
        }
    }

    @GetMapping("/agent/{mandatorAddress}")
    @ApiOperation(description = "查询代理人地址",order = 102)
    @Parameters({
            @Parameter(parameterName = "mandatorAddress", parameterDes = "委托人地址")
    })
    @io.nuls.core.rpc.model.ResponseData(name = "返回值", description = "代理人地址", responseType = @TypeDescriptor(value = String.class))
    public ResponseData agentAddress(@PathVariable String mandatorAddress) {
        try {
            String agentAddress = "";
            ResponseData result = ResponseData.success();
            result.setData(agentAddress);
            return result;
        } catch (Exception e) {
            logger.error("system error", e);
            return ResponseData.error(e.getMessage());
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/council/number")
    @ApiOperation(description = "查询理事会成员数量结构",order = 201)
    @io.nuls.core.rpc.model.ResponseData(name = "返回值", description = "查询理事会成员数量结构", responseType = @TypeDescriptor(value = CouncilMemberNumberDto.class))
    public ResponseData councilNumber() {
        try {
            CouncilMemberNumberDto dto = new CouncilMemberNumberDto();
            ResponseData result = ResponseData.success();
            result.setData(dto);
            return result;
        } catch (Exception e) {
            logger.error("system error", e);
            return ResponseData.error(e.getMessage());
        }
    }

    @GetMapping("/council/member/list")
    @ApiOperation(description = "查询理事会成员列表",order = 202)
    @io.nuls.core.rpc.model.ResponseData(name = "返回值", description = "理事会成员列表", responseType = @TypeDescriptor(value = List.class, collectionElement = CouncilMemberDto.class))
    public ResponseData councilMemberList() {
        try {
            List<CouncilMemberDto> mandatorList = new ArrayList<>();
            ResponseData result = ResponseData.success();
            result.setData(mandatorList);
            return result;
        } catch (Exception e) {
            logger.error("system error", e);
            return ResponseData.error(e.getMessage());
        }
    }

    @PostMapping("/council/applicant/list")
    @ApiOperation(description = "查询理事会申请人列表",order = 203)
    @Parameters({
            @Parameter(parameterName = "查询理事会申请人列表", parameterDes = "查询理事会申请人列表表单", requestType = @TypeDescriptor(value = CouncilApplicantSearch.class))
    })
    @io.nuls.core.rpc.model.ResponseData(name = "返回值", description = "理事会成员列表", responseType = @TypeDescriptor(value = List.class, collectionElement = CouncilApplicantDto.class))
    public ResponseData councilApplicantList(@RequestBody CouncilApplicantSearch councilApplicantSearch) {
        try {
            List<CouncilApplicantDto> applicantList = new ArrayList<>();
            ResponseData result = ResponseData.success();
            result.setData(applicantList);
            return result;
        } catch (Exception e) {
            logger.error("system error", e);
            return ResponseData.error(e.getMessage());
        }
    }

    @GetMapping("/council/member/{address}")
    @ApiOperation(description = "理事详情",order = 204)
    @Parameters({
            @Parameter(parameterName = "address", parameterDes = "理事成员地址")
    })
    @io.nuls.core.rpc.model.ResponseData(name = "返回值", description = "理事详情", responseType = @TypeDescriptor(value = CouncilMemberDetailDto.class))
    public ResponseData councilMemberDetails(@PathVariable String address) {
        try {
            CouncilMemberDetailDto dto = new CouncilMemberDetailDto();
            ResponseData result = ResponseData.success();
            result.setData(dto);
            return result;
        } catch (Exception e) {
            logger.error("system error", e);
            return ResponseData.error(e.getMessage());
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @PostMapping("/proposal/list")
    @ApiOperation(description = "查询提案列表",order = 301)
    @Parameters({
            @Parameter(parameterName = "查询提案列表", parameterDes = "查询提案列表表单", requestType = @TypeDescriptor(value = ProposaListSearch.class))
    })
    @io.nuls.core.rpc.model.ResponseData(name = "返回值", description = "提案列表", responseType = @TypeDescriptor(value = List.class, collectionElement = ProposalDto.class))
    public ResponseData proposalList(@RequestBody ProposaListSearch proposaListSearch) {
        try {
            List<ProposalDto> proposalList = new ArrayList<>();
            ResponseData result = ResponseData.success();
            result.setData(proposalList);
            return result;
        } catch (Exception e) {
            logger.error("system error", e);
            return ResponseData.error(e.getMessage());
        }
    }

    @GetMapping("/proposal/{proposalId}")
    @ApiOperation(description = "查询提案详情",order = 302)
    @Parameters({
            @Parameter(parameterName = "proposalId", parameterDes = "提案ID", requestType = @TypeDescriptor(value = int.class))
    })
    @io.nuls.core.rpc.model.ResponseData(name = "返回值", description = "提案详情", responseType = @TypeDescriptor(value = ProposalDetailDto.class))
    public ResponseData proposalDetails(@PathVariable Integer proposalId) {
        try {
            ProposalDetailDto dto = new ProposalDetailDto();
            ResponseData result = ResponseData.success();
            result.setData(dto);
            return result;
        } catch (Exception e) {
            logger.error("system error", e);
            return ResponseData.error(e.getMessage());
        }
    }

    @GetMapping("/proposal/{proposalId}/{itemId}")
    @ApiOperation(description = "查询提案选项详情",order = 303)
    @Parameters({
            @Parameter(parameterName = "proposalId", parameterDes = "提案ID", requestType = @TypeDescriptor(value = int.class)),
            @Parameter(parameterName = "itemId", parameterDes = "提案选项ID", requestType = @TypeDescriptor(value = int.class))
    })
    @io.nuls.core.rpc.model.ResponseData(name = "返回值", description = "提案选项详情", responseType = @TypeDescriptor(value = ProposalItemDetailDto.class))
    public ResponseData proposalItemDetails(@PathVariable Integer proposalId, @PathVariable Integer itemId) {
        try {
            ProposalItemDetailDto dto = new ProposalItemDetailDto();
            ResponseData result = ResponseData.success();
            result.setData(dto);
            return result;
        } catch (Exception e) {
            logger.error("system error", e);
            return ResponseData.error(e.getMessage());
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @PostMapping("/vote/list")
    @ApiOperation(description = "查询普通投票列表",order = 401)
    @Parameters({
            @Parameter(parameterName = "查询普通投票列表", parameterDes = "查询普通投票列表表单", requestType = @TypeDescriptor(value = VoteListSearch.class))
    })
    @io.nuls.core.rpc.model.ResponseData(name = "返回值", description = "普通投票列表", responseType = @TypeDescriptor(value = List.class, collectionElement = VoteDto.class))
    public ResponseData voteList(@RequestBody VoteListSearch voteListSearch) {
        try {
            List<VoteDto> voteList = new ArrayList<>();
            ResponseData result = ResponseData.success();
            result.setData(voteList);
            return result;
        } catch (Exception e) {
            logger.error("system error", e);
            return ResponseData.error(e.getMessage());
        }
    }

    @GetMapping("/vote/{voteId}")
    @ApiOperation(description = "查询投票详情",order = 402)
    @Parameters({
            @Parameter(parameterName = "voteId", parameterDes = "投票ID", requestType = @TypeDescriptor(value = int.class))
    })
    @io.nuls.core.rpc.model.ResponseData(name = "返回值", description = "投票详情", responseType = @TypeDescriptor(value = VoteDetailDto.class))
    public ResponseData voteDetails(@PathVariable Integer voteId) {
        try {
            VoteDetailDto dto = new VoteDetailDto();
            ResponseData result = ResponseData.success();
            result.setData(dto);
            return result;
        } catch (Exception e) {
            logger.error("system error", e);
            return ResponseData.error(e.getMessage());
        }
    }

    @GetMapping("/vote/{voteId}/{itemId}")
    @ApiOperation(description = "查询投票选项详情",order = 403)
    @Parameters({
            @Parameter(parameterName = "voteId", parameterDes = "投票ID", requestType = @TypeDescriptor(value = int.class)),
            @Parameter(parameterName = "itemId", parameterDes = "投票选项ID", requestType = @TypeDescriptor(value = int.class))
    })
    @io.nuls.core.rpc.model.ResponseData(name = "返回值", description = "投票选项详情", responseType = @TypeDescriptor(value = VoteItemDetailDto.class))
    public ResponseData voteItemDetails(@PathVariable Integer voteId, @PathVariable Integer itemId) {
        try {
            VoteItemDetailDto dto = new VoteItemDetailDto();
            ResponseData result = ResponseData.success();
            result.setData(dto);
            return result;
        } catch (Exception e) {
            logger.error("system error", e);
            return ResponseData.error(e.getMessage());
        }
    }
}
