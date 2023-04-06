<template>
  <div class="DocumentPassView">
    <AppIntroHeader HeaderPost="公文详情"></AppIntroHeader>
    <div class="DocumentPostAddViewbody">
      <el-form :model="documentForm" label-width="auto"
               class="demo-ruleForm mystify_form"
               size="mini"
               :hide-required-asterisk='hide_required' label-position="top">
        <el-form-item label="公文名称">
          <el-input v-model="documentForm.documentName" class="inputReadOnly" readonly="readonly"></el-input>
        </el-form-item>
        <el-form-item label="公文内容">
          <el-input type="textarea" v-model="documentForm.documentAbout" rows="8" class="inputReadOnly"
                    readonly="readonly"></el-input>
        </el-form-item>
        <el-form-item label="公文附件" v-show="documentForm.documentContent!=null">
          <div> {{ documentForm.documentContentName }}</div>
          <el-button type="primary" @click="downloadFile">下载</el-button>
        </el-form-item>
        <el-form-item label="批阅流程">
          <el-timeline class="documentTimeLine">
            <el-timeline-item
                v-for="(activity, index) in activities"
                :key="index"
                :timestamp="activity.timestamp"
                color='#0bbd87'
                placement="top">
              <div @click="$router.push('/userabout?userId='+activity.userId)">
                批阅者：{{ activity.userName }}
              </div>
            </el-timeline-item>
          </el-timeline>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import AppIntroHeader from "@/components/AppIntroHeader";
import request from "@/utils/request";
import Cookies from "js-cookie";

export default {
  name: "DocumentPassView",
  components: {AppIntroHeader},
  data() {
    return {
      documentForm: {
        documentName: '',
        documentAbout: '',
        documentClassId: null
      },
      activities: [],
      documentClass: {
        documentProcessCount: 0
      },
      documentExamerString: [],
      documentExameTimeString: [],
      hide_required: true,
    }
  },
  methods: {
    async loadDocumentById() {
      const documentId = parseInt(this.$route.query.documentId)
      await request.get("/document/getDocumentById/" + documentId).then(res => {
        if (res.code == '200') {
          this.documentForm = res.data
          if (this.documentForm.documentProcess > 0)
            this.handler()
        } else {
          this.$notify.error(res.msg)
        }
      })
    },
    handler() {
      this.documentExamerString = this.documentForm.documentExamerString.split('-')
      this.documentExameTimeString = this.documentForm.documentExameTimeString.split('|')
      for (let i = 0; i < this.documentForm.documentProcess; i++) {
        const documentExamerStringDetail = this.documentExamerString[i].split(':')
        const newActivities = {
          userName: documentExamerStringDetail[1],
          userId: documentExamerStringDetail[0],
          timestamp: this.documentExameTimeString[i]
        }
        this.activities.push(newActivities)
      }
    },
    downloadFile() {
      window.open(this.documentForm.documentContent)
    }
  },
  mounted() {
    this.loadDocumentById()
  },
}
</script>

<style lang="less" scoped>
.DocumentPassView {
  width: 100%;
  height: 100%;
  float: left;


  .documentTimeLine {
    text-align: left;
  }

  /deep/ .inputReadOnly {


    input {
      background-color: #cdd2c0;
    }

    textarea {
      background-color: #cdd2c0;
    }
  }

  /deep/ input {
    background-color: #ddded7;
  }

  /deep/ textarea {
    background-color: #ddded7;
    height: 50%;
  }

  .DocumentPostAddViewbody {
    height: 90%;

    .processAdd {
      height: 50px;

      .processInput {
        float: left;
        width: 43%;
        height: 50px;
        border: 1px solid #ddded7;

        /deep/ input {
          height: 50px;

        }
      }

      .processButton {
        margin: 0;
        padding: 0;
        float: left;
        text-align: center;
        width: 14%;
        height: 100%;

        /deep/ button {
          margin: 0;
          padding: 0;
          width: 90%;
          height: 49%;
          background-color: #dcded2;
          border: 1px solid #ddded7;
        }
      }
    }

    .DocumentPostStep {
      margin-bottom: 10%;
      border-top: 1px solid #d8dcbc;
      border-bottom: 1px solid #d8dcbc;
    }
  }
}
</style>