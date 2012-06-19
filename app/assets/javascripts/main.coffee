
lastState = {}

updateStatus = ->
  $.get "/status", (response) ->
    if response.state != lastState.state
      $(".current-ip input").val(response.ip)
      $(".status .name").text response.state

      $("body").attr "class", response.state

      tsLink = "ts3server://#{response.ip}"
      $("a.ts-link").attr("href", tsLink).text(tsLink)

      # Save state to prevent do the same when there is no changes
      lastState = response

$ ->
  # Every 5 seconds
  setInterval (-> updateStatus()), 5000

  $("body").attr "class", "stopped"
  updateStatus()

  $(".actions .action").click (event) ->
    url = $(event.target).closest(".action").data("url")
    $.post url, -> updateStatus()

